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
package fr.imag.adele.cadse.cadseg.teamwork;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import fr.imag.adele.cadse.cadseg.Activator;


/**
 * Represents the list of last used messages to commit.
 * These informations are by workspace.
 * 
 * @author Thomas
 * 
 */
public class LastCommitMsg {

	private static final String	MSG_PROP_PREFIX	= "teamwork.lastCommitMsg.";

	private static final int	_maxMsgNb		= 20;

	/**
	 * Returns list of last used messages for commit.
	 * A new list is returned for each call.
	 * 
	 * @return list of last used messages for commit.
	 */
	public static synchronized List<String> getLastCommitMsg() {
		List<String> msgList = new ArrayList<String>();
	
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		for (int i = 0; i < _maxMsgNb; i++) {
			String prefKey = MSG_PROP_PREFIX + i;
			if (!prefStore.contains(prefKey))
				continue;
			
			String msg = prefStore.getString(prefKey);
			if ((msg == null) || msg.isEmpty())
				continue;
			
			msgList.add(msg);
		}

		return msgList;
	}
	
	public static String[] getLastCommitMsgTab() {
		List<String> lastMsgs = getLastCommitMsg();
		
		return lastMsgs.toArray(new String[lastMsgs.size()]);
	}
	
	/**
	 * Saves last message used for commit.
	 * If there is no more space to keep all saved messages,
	 * the less recent one is discard.
	 * 
	 * @param msg a message used for commit
	 */
	public static synchronized void addLastCommitMsg(String msg) {
		List<String> msgList = getLastCommitMsg();
		
		// remove multiple occurency
		int duplicateIdx = -1;
		for (int i = 0; i < msgList.size(); i++) {
			String curMsg = msgList.get(i);
			if (!curMsg.trim().equals(msg.trim()))
				continue;
			
			duplicateIdx = i;
			break;
		}
		if (duplicateIdx != -1)
			msgList.remove(duplicateIdx);
		
		// add new message
		msgList.add(0, msg);
		
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		for (int i = 0; i < _maxMsgNb; i++) {
			String msgTostore = "";
			if (msgList.size() > i)
				msgTostore = msgList.get(i);
			
			prefStore.setValue(MSG_PROP_PREFIX + i, msgTostore);
		}
	}
}
