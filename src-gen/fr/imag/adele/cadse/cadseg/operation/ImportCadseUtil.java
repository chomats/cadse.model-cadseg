package fr.imag.adele.cadse.cadseg.operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.xml.bind.JAXBException;

import org.eclipse.core.resources.ResourcesPlugin;

import adele.util.io.ZipUtil;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseGCST;
import java.util.UUID;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.Link;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.ProjectAssociation;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.delta.ItemDelta;
import fr.imag.adele.cadse.core.delta.LinkDelta;
import fr.imag.adele.cadse.core.delta.SetAttributeOperation;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;

public class ImportCadseUtil {
	/**
	 * Read cadse.
	 * 
	 * @param f
	 *            the f
	 * 
	 * @return the string
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	static public String readCadse(File f) throws IOException, JAXBException {
		JarFile jis = new JarFile(f);
		ZipEntry entry = jis.getEntry(ExportCadsePagesAction.MELUSINE_DIR_CADSENAME);
		if (entry == null) {
			entry = jis.getEntry("/" + ExportCadsePagesAction.MELUSINE_DIR_CADSENAME);
			if (entry == null) {
				throw new IOException("Cannot found " + ExportCadsePagesAction.MELUSINE_DIR_CADSENAME);
			}
		}
		InputStream imput = jis.getInputStream(entry);
		BufferedReader isr = new BufferedReader(new InputStreamReader(imput));
		return isr.readLine();
	}

	static public String readCadseFolder(File f) throws IOException {
		File cadseNameFile = new File(f, ExportCadsePagesAction.MELUSINE_DIR_CADSENAME);
		if (!cadseNameFile.exists()) {
			throw new IOException("Cannot found " + ExportCadsePagesAction.MELUSINE_DIR_CADSENAME);
		}
		InputStream imput = new FileInputStream(cadseNameFile);
		BufferedReader isr = new BufferedReader(new InputStreamReader(imput));
		return isr.readLine();
	}

	/**
	 * Read cadse uuid.
	 * 
	 * @param f
	 *            the f
	 * 
	 * @return the compact uuid
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	static public UUID readCadseUUID(File f) throws IOException, JAXBException {
		JarFile jis = new JarFile(f);
		ZipEntry entry = jis.getEntry(ExportCadsePagesAction.MELUSINE_DIR_CADSENAME_ID);
		if (entry == null) {
			entry = jis.getEntry("/" + ExportCadsePagesAction.MELUSINE_DIR_CADSENAME_ID);
			if (entry == null) {
				throw new IOException("Cannot found " + ExportCadsePagesAction.MELUSINE_DIR_CADSENAME_ID);
			}
		}
		InputStream imput = jis.getInputStream(entry);
		BufferedReader isr = new BufferedReader(new InputStreamReader(imput));
		return new UUID(isr.readLine());
	}

	/**
	 * Read cadse uuid.
	 * 
	 * @param f
	 *            the f
	 * 
	 * @return the compact uuid
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 */
	static public UUID readCadseUUIDFolder(File f) throws IOException {
		File uuid = new File(f, ExportCadsePagesAction.MELUSINE_DIR_CADSENAME_ID);

		if (!uuid.exists()) {
			throw new IOException("Cannot found " + ExportCadsePagesAction.MELUSINE_DIR_CADSENAME_ID);

		}
		InputStream imput = new FileInputStream(uuid);
		BufferedReader isr = new BufferedReader(new InputStreamReader(imput));
		return new UUID(isr.readLine());
	}

	static private void migrate(LogicalWorkspaceTransaction transaction) throws CadseException {
		Collection<ItemDelta> operations = transaction.getItemOperations();
		for (ItemDelta itemDelta : operations) {

			if (itemDelta.getType() == null) {
				if (itemDelta.getBaseItem() != null) {
					itemDelta.setType(itemDelta.getBaseItem().getType());
				} else
					System.out.println("type has no type " + itemDelta);
			}

			if (!itemDelta.isLoaded())
				continue;

			SetAttributeOperation uuid_att = itemDelta.getSetAttributeOperation("UUID_ATTRIBUTE");
			if (uuid_att != null) {
				if (itemDelta.getType() == null) {
					System.out.println("Import error type is null");
				} else if (itemDelta.getType() == CadseGCST.CADSE_DEFINITION) {
					itemDelta.setAttribute(CadseGCST.CADSE_DEFINITION_at_ID_RUNTIME_, uuid_att.getCurrentValue());
				} else if (itemDelta.isInstanceOf(CadseGCST.PAGE)) {
					itemDelta.setAttribute(CadseGCST.PAGE_at_ID_RUNTIME_, uuid_att.getCurrentValue());
				} else if (itemDelta.isInstanceOf(CadseGCST.ATTRIBUTE)) {
					itemDelta.setAttribute(CadseGCST.ATTRIBUTE_at_ID_RUNTIME_, uuid_att.getCurrentValue());
				} else if (itemDelta.isInstanceOf(CadseGCST.TYPE_DEFINITION)) {
					itemDelta.setAttribute(CadseGCST.TYPE_DEFINITION_at_ID_RUNTIME_, uuid_att.getCurrentValue());
				} else {
					System.out.println("Cannot set UUID_ATTRIBUTE for type " + itemDelta.getType().getName());
				}
				// remove old valeur
				itemDelta.setAttribute("UUID_ATTRIBUTE", null);
			}
			if (itemDelta.getType() == CadseGCST.LINK) {
				if (itemDelta.getName().startsWith("#invert_part")) {
					itemDelta.delete(false);
					for (Link l : itemDelta.getIncomingLinks()) {
						l.delete();
					}
				}
				LinkDelta l = itemDelta.getOutgoingLink(CadseGCST.LINK_lt_INVERSE_LINK);
				if (l != null && l.getDestination().getName().startsWith("#invert_part")) {
					l.delete();
				}
			}
			SetAttributeOperation committed_date_value = itemDelta
					.getSetAttributeOperation(CadseGCST.ITEM_at_COMMITTED_DATE);
			if (committed_date_value != null) {
				if (committed_date_value.getCurrentValue() instanceof Date) {
					Date d = (Date) committed_date_value.getCurrentValue();
					itemDelta.setAttribute(CadseGCST.ITEM_at_COMMITTED_DATE_, d.getTime());
				}
			}
		}

		for (ItemDelta itemDelta : operations) {
			if (!itemDelta.isLoaded())
				continue;
			for (LinkDelta l : itemDelta.getOutgoingLinkOperations()) {
				if (l.getLinkTypeName().startsWith("#parent:") || l.getLinkTypeName().startsWith("#invert_part")) {
					if (itemDelta.getPartParent() == null) {
						itemDelta.setParent(l.getDestination(), null);
					}
					if (itemDelta.getOutgoingLink(CadseGCST.ITEM_lt_PARENT) == null) {
						itemDelta.createLink(CadseGCST.ITEM_lt_PARENT, l.getDestination());
					}
					l.delete();
				} else if (l.getDestination().getName().contains("#invert_part_")) {
					l.delete();
				}
				if (l.getLinkType() != null && l.getLinkType().isPart() && l.getDestination().getPartParent() == null) {
					l.getDestination().setParent(l.getSource(), l.getLinkType());
				}
			}
			for (LinkDelta l : itemDelta.getOutgoingLinkOperations(CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES)) {
				if (!l.isLoaded())
					continue;
				if (l.getDestination().getType() == null) {
					IAttributeType<?> att = l.getSource().getType().getAttributeType(l.getDestinationName(), false);

					if (att != null) {
						LinkDelta latt = itemDelta.getOutgoingLink(CadseGCST.ITEM_lt_MODIFIED_ATTRIBUTES, att.getId());
						if (latt != null) {
							l.delete();
						} else
							l.changeDestination(att);
					} else
						l.delete();
				}
			}
		}
		for (ItemDelta itemDelta : operations) {
			if (!itemDelta.isLoaded())
				continue;
			if (itemDelta.getPartParent() == null && itemDelta.getType() != null && itemDelta.getType().isPartType()) {
				System.out.println("Error cannot found parent for " + itemDelta.getQualifiedName());
			}
		}
	}

	static private void checkAction(LogicalWorkspaceTransaction transaction) {
		Collection<ItemDelta> operations = transaction.getItemOperations();
		LogicalWorkspace lw = CadseCore.getLogicalWorkspace();
		for (ItemDelta itemDelta : operations) {
			Item gI = lw.getItem(itemDelta.getId());
			if (gI == null) {
				System.err.println("Cannot found commited item " + itemDelta);
				continue;
			}
			Item parent = gI.getPartParent();
			if (parent == null && itemDelta.getPartParent() != null) {
				System.err.println("Parent not setted " + itemDelta + " -> " + itemDelta.getPartParent());
			} else {
				if (parent != null && itemDelta.getPartParent() != null) {
					if (!parent.getId().equals(itemDelta.getPartParent().getId())) {
						System.err.println("Parent not same " + itemDelta + " -> " + itemDelta.getPartParent() + "<>"
								+ parent);
					}
				}
			}

		}

	}

	/**
	 * 
	 * @param cadse
	 *            the name of the cadse : can be null, it read from
	 * @param input
	 * @return
	 * @throws IOException
	 * @throws CadseException
	 */
	static public Item importCadse(String cadse, InputStream input) throws IOException, CadseException {
		CadseCore.getCadseDomain().beginOperation("Import cadse");
		try {
			File pf;
			File dir = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
			if (cadse != null)
				pf = new File(dir, cadse);
			else
				pf = ImportCadseUtil.createTempDirectory(dir);

			ZipUtil.unzip(input, pf);
			UUID uuid = readCadseUUIDFolder(pf);
			if (cadse == null) {
				cadse = readCadseFolder(pf);
				pf.renameTo(new File(dir, cadse));
				pf = new File(dir, cadse);
			}

			File melusineDir = new File(pf, ".melusine-dir");
			File[] filesserxml = melusineDir.listFiles();
			Collection<URL> itemdescription = new ArrayList<URL>();
			for (File fser : filesserxml) {
				if (fser.getName().endsWith(".ser")) {
					itemdescription.add(fser.toURI().toURL());
				}
			}
			Collection<ProjectAssociation> projectAssociationSet = new ArrayList<ProjectAssociation>();
			ProjectAssociation pa = new ProjectAssociation(uuid, cadse);
			projectAssociationSet.add(pa);
			LogicalWorkspaceTransaction transaction = CadseCore.getLogicalWorkspace().createTransaction();

			transaction.loadItems(itemdescription);
			migrate(transaction);
			ItemDelta cadseDef = transaction.getItem(uuid);
			transaction.commit(false, true, false, projectAssociationSet);
			checkAction(transaction);
			return cadseDef.getBaseItem();
		} finally {
			CadseCore.getCadseDomain().endOperation();
		}
	}

	/**
	 * 
	 * @param dir
	 *            can be null : see
	 *            {@link File#createTempFile(String, String, File)}.
	 * @return a tempory folder
	 * @throws IOException
	 */

	public static File createTempDirectory(File dir) throws IOException {
		final File temp;

		temp = File.createTempFile("temp", Long.toString(System.nanoTime()), dir);

		if (!(temp.delete())) {
			throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
		}

		if (!(temp.mkdir())) {
			throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
		}

		return (temp);
	}

}
