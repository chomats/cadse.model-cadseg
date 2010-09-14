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
package fr.imag.adele.cadse.cadseg.model;

import fr.imag.adele.cadse.cadseg.type.EventType;

/**
 * The Class Event.
 * 
 * @author <a href="mailto:stephane.chomat@imag.fr">Stephane Chomat</a>
 */
public class Event {

	/**
	 * Instantiates a new event.
	 * 
	 * @param eventType
	 *            the event type
	 */
	public Event(EventType eventType) {
		this(eventType, "", "");
	}

	/**
	 * Instantiates a new event.
	 * 
	 * @param eventType
	 *            the event type
	 * @param cond
	 *            the cond
	 * @param body
	 *            the body
	 */
	public Event(EventType eventType, String cond, String body) {
		type = eventType;
		condition = cond;
		this.body = body;
	}

	/**
	 * Gets the body.
	 * 
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Gets the condition.
	 * 
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}

	/** The type. */
	EventType	type;

	/** The condition. */
	String		condition;

	/** The body. */
	String		body;
}
