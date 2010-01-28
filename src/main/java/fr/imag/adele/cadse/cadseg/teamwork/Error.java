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

import java.util.UUID;


/**
 * Represents one error attached to one item.
 * 
 * @author Thomas
 *
 */
public interface Error {

	/**
	 * Return item which is related to this error.
	 * 
	 * @return item which is related to this error.
	 */
	public UUID getItem();
	
	/**
	 * Return the message describing the error.
	 * 
	 * @return the message describing the error.
	 */
	public String getMessage();
	
	/**
	 * Returns true if this error has been resolved or accepted by the user.
	 * 
	 * @return true if this error has been resolved or accepted by the user.
	 */
	public boolean isResolved();
}
