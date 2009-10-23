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
 */
package fr.imag.adele.cadse.cadseg.managers;

import java.io.File;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import fede.workspace.tool.loadmodel.model.jaxb.CCadse;
import fede.workspace.tool.loadmodel.model.jaxb.CItemType;
import fede.workspace.tool.loadmodel.model.jaxb.CLink;
import fede.workspace.tool.loadmodel.model.jaxb.CLinkType;
import fede.workspace.tool.loadmodel.model.jaxb.CPages;
import fede.workspace.tool.loadmodel.model.jaxb.CValuesType;

public class Correction {
	static final public String notnull(String v) {
		if (v == null)
			return "##null##";
		return v;
	}

	public static interface KeyAccess<T> {
		String getKey(T o);

	}

	private static final class CItemTypeComparator implements Comparator<CItemType>, KeyAccess<CItemType> {
		public int compare(CItemType o1, CItemType o2) {
			return o1.getName().compareTo(o2.getName());
		}

		public String getKey(CItemType o) {
			return o.getName();
		}

	}

	private static final class ComparatorAccess<T> implements Comparator<T> {
		private KeyAccess<T>	a;

		public ComparatorAccess(KeyAccess<T> a) {
			this.a = a;
		}

		public int compare(T o1, T o2) {
			return notnull(a.getKey(o1)).compareTo(notnull(a.getKey(o2)));
		}

	}

	private static final class CValuesTypeComparator implements Comparator<CValuesType>, KeyAccess<CValuesType> {
		public int compare(CValuesType o1, CValuesType o2) {
			return (notnull(o1.getKey())).compareTo(notnull(o2.getKey()));
		}

		public String getKey(CValuesType o) {
			return o.getKey();
		}

	}

	private static final class CLinkTypeComparator implements Comparator<CLinkType>, KeyAccess<CLinkType> {
		public int compare(CLinkType o1, CLinkType o2) {
			if (o1 instanceof CLinkType && o2 instanceof CLinkType)
				return ((CLinkType) o1).getName().compareTo(((CLinkType) o2).getName());
			return -1;
		}

		public String getKey(CLinkType o) {
			if (o instanceof CLinkType)
				return ((CLinkType) o).getName();
			return o.getDestinationId() + o.getType();
		}
	}

	public static void main(String[] args) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance("fede.workspace.tool.loadmodel.model.jaxb");

		Unmarshaller um = jc.createUnmarshaller();
		CCadse cadse = (CCadse) um.unmarshal(new File(
				"/home/chomats/ws-cadseg1/Model.Workspace.Workspace/model/cadse.xml"));
		CCadse cadse2 = (CCadse) um.unmarshal(new File(
				"/home/chomats/ws-cadseg1/Model.Workspace.Workspace/model/Copy of cadse.xml"));

		merge(cadse, cadse2);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter write = new StringWriter();
		m.marshal(cadse, write);
		System.out.println(write.toString());

	}

	private static void merge(CCadse cadse, CCadse cadse2) {
		HashMap<String, CItemType> types1 = new HashMap<String, CItemType>();
		HashMap<String, CItemType> types2 = new HashMap<String, CItemType>();
		typesToMap(cadse, types1);
		typesToMap(cadse2, types2);
		List<CItemType> listOfItemTypes = cadse.getItemType();
		CItemType[] values = (CItemType[]) listOfItemTypes.toArray(new CItemType[listOfItemTypes.size()]);

		for (CItemType ci : values) {
			CItemType ci2 = types2.get(ci.getName());
			if (ci2 != null) {
				merge(ci, ci2);
			}
		}

		List<CItemType> listOfItemTypes2 = cadse2.getItemType();
		CItemType[] values2 = (CItemType[]) listOfItemTypes2.toArray(new CItemType[listOfItemTypes.size()]);
		for (CItemType ci : values) {
			CItemType ci1 = types1.get(ci.getName());
			if (ci1 == null) {
				listOfItemTypes.add(ci1);
			}
		}
		// sort
		values = (CItemType[]) listOfItemTypes.toArray(new CItemType[listOfItemTypes.size()]);
		Arrays.sort(values, new CItemTypeComparator());
		listOfItemTypes.clear();
		listOfItemTypes.addAll(Arrays.asList(values));
	}

	private static void merge(CItemType ci, CItemType ci2) {
		CPages o11 = ci.getCreationPages();
		CPages o21 = ci2.getCreationPages();
		if (o11 == null && o21 != null) {
			ci.setCreationPages(o21);
		}

		CPages o12 = ci.getModificationPages();
		CPages o22 = ci2.getModificationPages();
		if (o12 == null && o22 != null) {
			ci.setModificationPages(o22);
		}

		mergeLink(ci.getOutgoingLink(), ci2.getOutgoingLink());
		mergeAttribe(ci.getAttributeType(), ci2.getAttributeType());

	}

	private static void mergeAttribe(List<CValuesType> attributeType, List<CValuesType> attributeType2) {
		mergeList(attributeType, attributeType2, new CValuesTypeComparator());
	}

	private static <T> void mergeList(List<T> l1, List<T> l2, KeyAccess<T> access) {
		Map<String, T> m1 = new HashMap<String, T>();
		Map<String, T> m2 = new HashMap<String, T>();
		for (T t : l1) {
			String key = access.getKey(t);
			if (key == null)
				continue;
			m1.put(key, t);
		}
		for (T t : l2) {
			String key = access.getKey(t);
			if (key == null)
				continue;
			if (!m1.containsKey(key)) {
				l1.add(t);
				m1.put(key, t);
			}
		}
		Object[] values = l1.toArray();
		Arrays.sort(values, (Comparator<? super Object>) new ComparatorAccess<T>(access));
		l1.clear();
		for (Object o : values) {
			l1.add((T) o);
		}
	}

	private static void mergeLink(List<CLinkType> outgoingLink, List<CLinkType> outgoingLink2) {
		mergeList(outgoingLink, outgoingLink2, new CLinkTypeComparator());
	}

	private static void typesToMap(CCadse cadse, HashMap<String, CItemType> types1) {
		for (CItemType ci : cadse.getItemType()) {
			types1.put(ci.getName(), ci);
		}
	}

}
