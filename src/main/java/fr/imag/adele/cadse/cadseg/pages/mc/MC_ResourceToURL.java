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
package fr.imag.adele.cadse.cadseg.pages.mc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

import org.eclipse.core.internal.boot.PlatformURLHandler;
import org.eclipse.core.internal.utils.Messages;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;

import fr.imag.adele.cadse.core.impl.ui.mc.MC_AttributesItem;
import fr.imag.adele.cadse.core.ui.UIField;

public class MC_ResourceToURL extends MC_AttributesItem {

	public static final String RESOURCE = "resource"; //$NON-NLS-1$
	public static final String RESOURCE_URL_STRING = PlatformURLHandler.PROTOCOL + PlatformURLHandler.PROTOCOL_SEPARATOR + '/' + RESOURCE + '/';
	
	
	public MC_ResourceToURL() {
		super();
	}

	@Override
	public Object getValue() {
		return abstractToVisualValue(super.getValue());
	}

	@Override
	public void notifieValueChanged(UIField field, Object value) {
		super.notifieValueChanged(field, visualToAbstractValue(value));
	}

	@Override
	public boolean validValue(UIField field, Object value) {
		return super.validValue(field, visualToAbstractValue(value));
	}

	@Override
	public boolean validValueChanged(UIField field, Object visualValue) {
		return super.validValueChanged(field, visualToAbstractValue(visualValue));
	}

	protected Object abstractToVisualValue(Object value) {
		String rString = (String) value;

		if (rString == null) {
			return null;
		}
		if (rString.startsWith(RESOURCE_URL_STRING)) {
			try {
				return get(new URL(rString));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		
		return root.findMember(new Path(rString));

	}

	IResource get(URL url) throws IOException {
		String filePath = url.getFile().trim();
		filePath = URLDecoder.decode(filePath, "UTF-8"); //$NON-NLS-1$
		IPath spec = new Path(filePath).makeRelative();
		if (!spec.segment(0).equals(RESOURCE))
			throw new IOException(NLS.bind(Messages.url_badVariant, url));
		int count = spec.segmentCount();
		// if there is only one segment then we are talking about the workspace root.
		if (count == 1)
			return ResourcesPlugin.getWorkspace().getRoot();
		// if there are two segments then the second is a project name.
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(spec.segment(1));
		
		IResource resource = null;
		if (count == 2) {
			resource = project;
		} else {
			spec = spec.removeFirstSegments(2);
			resource = project.getFile(spec);
		}
		return resource;
	}
	
	protected Object visualToAbstractValue(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof IResource) {
			IResource r = (IResource) value;
			return RESOURCE_URL_STRING+r.getFullPath().makeRelative().toPortableString();
		}
		return value.toString();
	}

	@Override
	public Object defaultValue() {
		return null;
	}
}
