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

package fr.imag.adele.cadse.cadseg.fields;

import org.eclipse.osgi.util.NLS;

/**
 * RegEx messages. Helper class to get NLSed messages.
 * 
 * @since 3.1
 */
final class RegExMessages extends NLS {

	/** The Constant BUNDLE_NAME. */
	private static final String	BUNDLE_NAME	= RegExMessages.class.getName();

	/**
	 * Instantiates a new reg ex messages.
	 */
	private RegExMessages() {
		// Do not instantiate
	}

	static {
		reloadMessages();
	}

	/**
	 * Reload messages.
	 */
	static void reloadMessages() {
		NLS.initializeMessages(BUNDLE_NAME, RegExMessages.class);
	}

	// characters
	/** The display string_bs_bs. */
	public static String	displayString_bs_bs;

	/** The additional info_bs_bs. */
	public static String	additionalInfo_bs_bs;

	/** The display string_bs_0. */
	public static String	displayString_bs_0;

	/** The additional info_bs_0. */
	public static String	additionalInfo_bs_0;

	/** The display string_bs_x. */
	public static String	displayString_bs_x;

	/** The additional info_bs_x. */
	public static String	additionalInfo_bs_x;

	/** The display string_bs_u. */
	public static String	displayString_bs_u;

	/** The additional info_bs_u. */
	public static String	additionalInfo_bs_u;

	/** The display string_bs_t. */
	public static String	displayString_bs_t;

	/** The additional info_bs_t. */
	public static String	additionalInfo_bs_t;

	/** The display string_bs_n. */
	public static String	displayString_bs_n;

	/** The additional info_bs_n. */
	public static String	additionalInfo_bs_n;

	/** The display string_bs_r. */
	public static String	displayString_bs_r;

	/** The additional info_bs_r. */
	public static String	additionalInfo_bs_r;

	/** The display string_bs_f. */
	public static String	displayString_bs_f;

	/** The additional info_bs_f. */
	public static String	additionalInfo_bs_f;

	/** The display string_bs_a. */
	public static String	displayString_bs_a;

	/** The additional info_bs_a. */
	public static String	additionalInfo_bs_a;

	/** The display string_bs_e. */
	public static String	displayString_bs_e;

	/** The additional info_bs_e. */
	public static String	additionalInfo_bs_e;

	/** The display string_bs_c. */
	public static String	displayString_bs_c;

	/** The additional info_bs_c. */
	public static String	additionalInfo_bs_c;

	// character classes
	/** The display string_dot. */
	public static String	displayString_dot;

	/** The additional info_dot. */
	public static String	additionalInfo_dot;

	/** The display string_bs_d. */
	public static String	displayString_bs_d;

	/** The additional info_bs_d. */
	public static String	additionalInfo_bs_d;

	/** The display string_bs_ d. */
	public static String	displayString_bs_D;

	/** The additional info_bs_ d. */
	public static String	additionalInfo_bs_D;

	/** The display string_bs_s. */
	public static String	displayString_bs_s;

	/** The additional info_bs_s. */
	public static String	additionalInfo_bs_s;

	/** The display string_bs_ s. */
	public static String	displayString_bs_S;

	/** The additional info_bs_ s. */
	public static String	additionalInfo_bs_S;

	/** The display string_bs_w. */
	public static String	displayString_bs_w;

	/** The additional info_bs_w. */
	public static String	additionalInfo_bs_w;

	/** The display string_bs_ w. */
	public static String	displayString_bs_W;

	/** The additional info_bs_ w. */
	public static String	additionalInfo_bs_W;

	// boundary matchers
	/** The display string_start. */
	public static String	displayString_start;

	/** The additional info_start. */
	public static String	additionalInfo_start;

	/** The display string_end. */
	public static String	displayString_end;

	/** The additional info_end. */
	public static String	additionalInfo_end;

	/** The display string_bs_b. */
	public static String	displayString_bs_b;

	/** The additional info_bs_b. */
	public static String	additionalInfo_bs_b;

	/** The display string_bs_ b. */
	public static String	displayString_bs_B;

	/** The additional info_bs_ b. */
	public static String	additionalInfo_bs_B;

	/** The display string_bs_ a. */
	public static String	displayString_bs_A;

	/** The additional info_bs_ a. */
	public static String	additionalInfo_bs_A;

	/** The display string_bs_ g. */
	public static String	displayString_bs_G;

	/** The additional info_bs_ g. */
	public static String	additionalInfo_bs_G;

	/** The display string_bs_ z. */
	public static String	displayString_bs_Z;

	/** The additional info_bs_ z. */
	public static String	additionalInfo_bs_Z;

	/** The display string_bs_z. */
	public static String	displayString_bs_z;

	/** The additional info_bs_z. */
	public static String	additionalInfo_bs_z;

	// greedy quantifiers
	/** The display string_quest. */
	public static String	displayString_quest;

	/** The additional info_quest. */
	public static String	additionalInfo_quest;

	/** The display string_star. */
	public static String	displayString_star;

	/** The additional info_star. */
	public static String	additionalInfo_star;

	/** The display string_plus. */
	public static String	displayString_plus;

	/** The additional info_plus. */
	public static String	additionalInfo_plus;

	/** The display string_exact. */
	public static String	displayString_exact;

	/** The additional info_exact. */
	public static String	additionalInfo_exact;

	/** The display string_least. */
	public static String	displayString_least;

	/** The additional info_least. */
	public static String	additionalInfo_least;

	/** The display string_count. */
	public static String	displayString_count;

	/** The additional info_count. */
	public static String	additionalInfo_count;

	// lazy quantifiers
	/** The display string_quest lazy. */
	public static String	displayString_questLazy;

	/** The additional info_quest lazy. */
	public static String	additionalInfo_questLazy;

	/** The display string_star lazy. */
	public static String	displayString_starLazy;

	/** The additional info_star lazy. */
	public static String	additionalInfo_starLazy;

	/** The display string_plus lazy. */
	public static String	displayString_plusLazy;

	/** The additional info_plus lazy. */
	public static String	additionalInfo_plusLazy;

	/** The display string_exact lazy. */
	public static String	displayString_exactLazy;

	/** The additional info_exact lazy. */
	public static String	additionalInfo_exactLazy;

	/** The display string_least lazy. */
	public static String	displayString_leastLazy;

	/** The additional info_least lazy. */
	public static String	additionalInfo_leastLazy;

	/** The display string_count lazy. */
	public static String	displayString_countLazy;

	/** The additional info_count lazy. */
	public static String	additionalInfo_countLazy;

	// possessive quantifiers
	/** The display string_quest poss. */
	public static String	displayString_questPoss;

	/** The additional info_quest poss. */
	public static String	additionalInfo_questPoss;

	/** The display string_star poss. */
	public static String	displayString_starPoss;

	/** The additional info_star poss. */
	public static String	additionalInfo_starPoss;

	/** The display string_plus poss. */
	public static String	displayString_plusPoss;

	/** The additional info_plus poss. */
	public static String	additionalInfo_plusPoss;

	/** The display string_exact poss. */
	public static String	displayString_exactPoss;

	/** The additional info_exact poss. */
	public static String	additionalInfo_exactPoss;

	/** The display string_least poss. */
	public static String	displayString_leastPoss;

	/** The additional info_least poss. */
	public static String	additionalInfo_leastPoss;

	/** The display string_count poss. */
	public static String	displayString_countPoss;

	/** The additional info_count poss. */
	public static String	additionalInfo_countPoss;

	// alternative
	/** The display string_alt. */
	public static String	displayString_alt;

	/** The additional info_alt. */
	public static String	additionalInfo_alt;

	// capturing groups
	/** The display string_group. */
	public static String	displayString_group;

	/** The additional info_group. */
	public static String	additionalInfo_group;

	/** The display string_bs_i. */
	public static String	displayString_bs_i;

	/** The additional info_bs_i. */
	public static String	additionalInfo_bs_i;

	// quoting
	/** The display string_bs. */
	public static String	displayString_bs;

	/** The additional info_bs. */
	public static String	additionalInfo_bs;

	/** The display string_bs_ q. */
	public static String	displayString_bs_Q;

	/** The additional info_bs_ q. */
	public static String	additionalInfo_bs_Q;

	/** The display string_bs_ e. */
	public static String	displayString_bs_E;

	/** The additional info_bs_ e. */
	public static String	additionalInfo_bs_E;

	// character sets
	/** The display string_set. */
	public static String	displayString_set;

	/** The additional info_set. */
	public static String	additionalInfo_set;

	/** The display string_set excl. */
	public static String	displayString_setExcl;

	/** The additional info_set excl. */
	public static String	additionalInfo_setExcl;

	/** The display string_set range. */
	public static String	displayString_setRange;

	/** The additional info_set range. */
	public static String	additionalInfo_setRange;

	/** The display string_set inter. */
	public static String	displayString_setInter;

	/** The additional info_set inter. */
	public static String	additionalInfo_setInter;

	/** The display string_posix. */
	public static String	displayString_posix;

	/** The additional info_posix. */
	public static String	additionalInfo_posix;

	/** The display string_posix not. */
	public static String	displayString_posixNot;

	/** The additional info_posix not. */
	public static String	additionalInfo_posixNot;

	/** The display string_flag. */
	public static String	displayString_flag;

	/** The additional info_flag. */
	public static String	additionalInfo_flag;

	/** The display string_flag expr. */
	public static String	displayString_flagExpr;

	/** The additional info_flag expr. */
	public static String	additionalInfo_flagExpr;

	// non-capturing group
	/** The display string_non cap. */
	public static String	displayString_nonCap;

	/** The additional info_non cap. */
	public static String	additionalInfo_nonCap;

	/** The display string_atomic cap. */
	public static String	displayString_atomicCap;

	/** The additional info_atomic cap. */
	public static String	additionalInfo_atomicCap;

	// look-ahead
	/** The display string_pos lookahead. */
	public static String	displayString_posLookahead;

	/** The additional info_pos lookahead. */
	public static String	additionalInfo_posLookahead;

	/** The display string_neg lookahead. */
	public static String	displayString_negLookahead;

	/** The additional info_neg lookahead. */
	public static String	additionalInfo_negLookahead;

	/** The display string_pos lookbehind. */
	public static String	displayString_posLookbehind;

	/** The additional info_pos lookbehind. */
	public static String	additionalInfo_posLookbehind;

	/** The display string_neg lookbehind. */
	public static String	displayString_negLookbehind;

	/** The additional info_neg lookbehind. */
	public static String	additionalInfo_negLookbehind;

	// replace
	/** The display string_dollar. */
	public static String	displayString_dollar;

	/** The additional info_dollar. */
	public static String	additionalInfo_dollar;

	/** The additional info_replace_bs. */
	public static String	additionalInfo_replace_bs;

	/** The display string_replace_bs. */
	public static String	displayString_replace_bs;

	/** The display string_tab. */
	public static String	displayString_tab;

	/** The additional info_tab. */
	public static String	additionalInfo_tab;

	/** The display string_nl. */
	public static String	displayString_nl;

	/** The additional info_nl. */
	public static String	additionalInfo_nl;

	/** The display string_cr. */
	public static String	displayString_cr;

	/** The additional info_cr. */
	public static String	additionalInfo_cr;
}
