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

package fr.imag.adele.cadse.cadseg.type;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 * The Class AEHasAttributeValue.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class AEHasAttributeValue extends AEObject {

	/** The av. */
	private AttributeValue[]	av	= null;

	/** The aa. */
	private Attribute[]			aa	= null;

	/**
	 * Instantiates a new aE has attribute value.
	 * 
	 * @param ce
	 *            the ce
	 */
	public AEHasAttributeValue(IConfigurationElement ce) {
		super(ce);
	}

	/**
	 * Gets the attribute value.
	 * 
	 * @return the attribute value
	 */
	public AttributeValue[] getAttributeValue() {
		IConfigurationElement[] v = ce.getChildren("attribute-value");
		if (av == null) {
			av = new AttributeValue[v.length];
			for (int i = 0; i < av.length; i++) {
				av[i] = new AttributeValue(v[i]);
			}
		}
		return this.av;
	}

	/**
	 * Gets the attribute.
	 * 
	 * @return the attribute
	 */
	public Attribute[] getAttribute() {
		IConfigurationElement[] v = ce.getChildren("attribute");
		if (aa == null) {
			aa = new Attribute[v.length];
			for (int i = 0; i < aa.length; i++) {
				aa[i] = new Attribute(v[i]);
			}
		}
		return this.aa;
	}

	// public IFieldDescription[] getFieldDescriptions() throws CoreException {
	// Attribute[] aa = getAttribute();
	// IFieldDescription[] fd = new IFieldDescription[aa.length];
	//		
	// for (int i = 0; i < fd.length; i++) {
	// fd[i] = aa[i].createField();
	// }
	// return fd;
	// }

	/**
	 * Gets the clazz.
	 * 
	 * @return the clazz
	 */
	public String getClazz() {
		String value = ce.getAttribute("class");
		if (value == null) {
			AEHasAttributeValue superUI = getSuper();
			if (superUI != null)
				return superUI.getClazz();
		}
		return value;
	}

	/**
	 * Gets the super.
	 * 
	 * @return the super
	 */
	AEHasAttributeValue getSuper() {
		String superUI = ce.getAttribute("super");
		if (superUI == null)
			return null;
		if (superUI.length() == 0)
			return null;
		return getTypeFromString(superUI);
	}

	/**
	 * Gets the type from string.
	 * 
	 * @param superUI
	 *            the super ui
	 * 
	 * @return the type from string
	 */
	protected AEHasAttributeValue getTypeFromString(String superUI) {
		return null;
	}

	/**
	 * Creates the executable.
	 * 
	 * @return the object
	 * 
	 * @throws CoreException
	 *             the core exception
	 */
	protected Object createExecutable() throws CoreException {
		String clazz = getClazz();
		if (clazz != null) {
			Object o = ce.createExecutableExtension("clazz");
			return o;
		}
		return null;
	}

	// @Override
	// public void completeField(UIField fd) throws CoreException {
	// AttributeValue[] av = getAttributeValue();
	// for (int i = 0; i < av.length; i++) {
	// fd.put(av[i].getKey(), av[i].getValue());
	// }
	// AEHasAttributeValue uc = getSuper();
	// if (uc != null) uc.completeField(fd);
	// super.completeField(fd);
	// }

}
