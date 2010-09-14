/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.cadseg.debug;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;

import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CCadse;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CExtensionItemType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CItemType;
import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CPages;

public class RemovePages {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		try {
			CCadse cadse = readAndRemovePages(args[0]);
			StringWriter writer = new StringWriter();

			JAXBContext jc = JAXBContext.newInstance("fr.imag.adele.fede.workspace.as.initmodel.jaxb");
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(cadse, writer);
			FileUtils.copyStream(new ByteArrayInputStream(writer.toString().getBytes()), true,
					new FileOutputStream(new File(args[0])), true);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

	private static CCadse readAndRemovePages(String f) throws FileNotFoundException, JAXBException {
		CCadse c = read(new FileInputStream(new File(f)));
		for(CItemType it : c.getItemType()) {
			CPages creationPages = it.getCreationPages();
			if (creationPages != null)
				it.setDefaultShortName(creationPages.getDefaultShortName());
			it.setCreationPages(null);
			it.setModificationPages(null);
		}
		for (CExtensionItemType cet : c.getExtItemType()) {
			cet.setCreationPages(null);
			cet.setModificationPages(null);
		}
		return c;
	}
	
	static public CCadse read(InputStream s) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance("fr.imag.adele.fede.workspace.as.initmodel.jaxb");
		Unmarshaller m = jc.createUnmarshaller();
		return (CCadse) m.unmarshal(s);
	}

}
