/*
 * BSD 2-Clause License
 *
 * Copyright (c) 2020, Lars van Soest
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.larsvansoest.runelite.clueitems;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

/**
 * Config setup for the {@link com.larsvansoest.runelite.clueitems.EmoteClueItemsPlugin}.
 * <p>
 * Allows for enabling plugin user settings in Runelite.
 *
 * @author Lars van Soest
 * @since 1.0.4
 */
@SuppressWarnings("SameReturnValue")
@ConfigGroup("emote-clue-items")
public interface EmoteClueItemsConfig extends Config
{
	@ConfigSection(name = "Collection Log Panel",
			       description = "Modify the plugin panel's behaviour.",
			       position = 0)
	String Section_collectionLogPanel = "collectionLogPanel";

	@ConfigSection(name = "Interface Tracking",
			description = "Toggle including items in the collection log per interface type.",
			position = 1)
	String Section_interfaceTracking = "interfaceTracking";

	@ConfigSection(name = "Interface Highlighting",
	               description = "Toggle emote clue icons per interface type.",
	               position = 2)
	String Section_interfaceHighlighting = "interfaceHighlighting";

	@ConfigSection(name = "Difficulty Highlighting",
			description = "Toggle emote clue icons per difficulty.",
			position = 3)
	String Section_difficultyHighlighting = "difficultyHighlighting";

    // Collection Log Panel

	@ConfigItem(keyName = "ShowNavigation",
	            name = "Show collection log",
	            description = "Show the collection log panel in the RuneLite toolbar.",
			    section = Section_collectionLogPanel,
	            position = 0)
	default boolean showNavigation()
	{
		return true;
	}

	@ConfigItem(keyName = "NotifyUnopenedInterfaces",
			name = "Unopened interface notification",
			description = "Show notification for tracked interfaces that have not yet been opened.",
			section = Section_collectionLogPanel,
			position = 1)
	default boolean notifyUnopenedInterfaces()
	{
		return true;
	}

	@ConfigItem(keyName = "AutoSyncWatsonBoard",
			name = "Sync watson board",
			description = "Sync STASH unit fill statuses after opening the notice board in Watson's house.",
			section = Section_collectionLogPanel,
			position = 1)
	default boolean autoSyncWatsonBoard()
	{
		return false;
	}

	// Interface Tracking

	@ConfigItem(keyName = "TrackBank",
			name = "Bank",
			description = "Include bank items in the collection log.",
			section = Section_interfaceTracking,
			position = 0)
	default boolean trackBank()
	{
		return true;
	}

	@ConfigItem(keyName = "TrackInventory",
			name = "Inventory",
			description = "Include inventory items in the collection log.",
			section = Section_interfaceTracking,
			position = 1)
	default boolean trackInventory()
	{
		return true;
	}

	@ConfigItem(keyName = "TrackEquipment",
			name = "Equipment",
			description = "Include equipped items in the collection log.",
			section = Section_interfaceTracking,
			position = 2)
	default boolean trackEquipment()
	{
		return true;
	}

	@ConfigItem(keyName = "TrackGroupStorage",
			name = "Group Storage",
			description = "(Group iron men) include group storage items in the collection log.",
			section = Section_interfaceTracking,
			position = 3)
	default boolean trackGroupStorage()
	{
		return false;
	}

	// Interface Highlighting

	@ConfigItem(keyName = "HighlightBank",
	            name = "Bank",
	            description = "Show highlights on bank interface.",
	            section = Section_interfaceHighlighting,
	            position = 0)
	default boolean highlightBank()
	{
		return true;
	}

	@ConfigItem(keyName = "HighlightInventory",
	            name = "Inventory",
	            description = "Show highlights on inventory interface.",
	            section = Section_interfaceHighlighting,
	            position = 1)
	default boolean highlightInventory()
	{
		return true;
	}

	@ConfigItem(keyName = "HighlightDepositBox",
	            name = "Deposit Box",
	            description = "Show highlights on deposit box interface.",
	            section = Section_interfaceHighlighting,
	            position = 2)
	default boolean highlightDepositBox()
	{
		return false;
	}

	@ConfigItem(keyName = "HighlightEquipment",
	            name = "Equipment",
	            description = "Show highlights on equipment interface.",
	            section = Section_interfaceHighlighting,
	            position = 3)
	default boolean highlightEquipment()
	{
		return false;
	}

	@ConfigItem(keyName = "HighlightGuidePrices",
	            name = "Guide Prices",
	            description = "Show highlights on guide prices interface.",
	            section = Section_interfaceHighlighting,
	            position = 4)
	default boolean highlightGuidePrices()
	{
		return false;
	}

	@ConfigItem(keyName = "HighlightKeptOnDeath",
	            name = "Kept on Death",
	            description = "Show highlights on kept on death interface.",
	            section = Section_interfaceHighlighting,
	            position = 5)
	default boolean highlightKeptOnDeath()
	{
		return false;
	}

	@ConfigItem(keyName = "HighlightShop",
	            name = "Shops",
	            description = "Show highlights on shop interfaces.",
	            section = Section_interfaceHighlighting,
	            position = 6)
	default boolean highlightShop()
	{
		return false;
	}

	@ConfigItem(keyName = "HighlightGroupStorage",
	            name = "Group Storage",
	            description = "Show highlights on group iron man storage.",
	            section = Section_interfaceHighlighting,
	            position = 7)
	default boolean highlightGroupStorage()
	{
		return false;
	}

	// Difficulty Highlighting

	@ConfigItem(keyName = "HighlightBeginner",
			name = "Beginner",
			description = "Show highlights for beginner clues.",
			section = Section_difficultyHighlighting,
			position = 1)
	default boolean highlightBeginner()
	{
		return true;
	}

	@ConfigItem(keyName = "HighlightEasy",
			name = "Easy",
			description = "Show highlights for easy clues.",
			section = Section_difficultyHighlighting,
			position = 2)
	default boolean highlightEasy()
	{
		return true;
	}

	@ConfigItem(keyName = "HighlightMedium",
			name = "Medium",
			description = "Show highlights for medium clues.",
			section = Section_difficultyHighlighting,
			position = 3)
	default boolean highlightMedium()
	{
		return true;
	}

	@ConfigItem(keyName = "HighlightHard",
			name = "Hard",
			description = "Show highlights for hard clues.",
			section = Section_difficultyHighlighting,
			position = 4)
	default boolean highlightHard()
	{
		return true;
	}

	@ConfigItem(keyName = "HighlightElite",
			name = "Elite",
			description = "Show highlights for elite clues.",
			section = Section_difficultyHighlighting,
			position = 5)
	default boolean highlightElite()
	{
		return true;
	}

	@ConfigItem(keyName = "HighlightMaster",
			name = "Master",
			description = "Show highlights for master clues.",
			section = Section_difficultyHighlighting,
			position = 6)
	default boolean highlightMaster()
	{
		return true;
	}

	// Below the sections

	@ConfigItem(keyName = "FilterInStash",
	            name = "Filter items in STASH",
	            description = "Do not highlight items already in STASH units.",
	            position = 4)
	default boolean filterInStash()
	{
		return true;
	}
}