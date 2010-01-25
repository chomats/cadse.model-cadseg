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
import fr.imag.adele.cadse.core.transaction.delta.ItemDelta;
import fr.imag.adele.cadse.core.transaction.delta.LinkDelta;
import fr.imag.adele.cadse.core.transaction.delta.SetAttributeOperation;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.core.transaction.LogicalWorkspaceTransaction;

@Deprecated
public class ImportCadseUtil extends ExportImportCadseFunction {
	
}
