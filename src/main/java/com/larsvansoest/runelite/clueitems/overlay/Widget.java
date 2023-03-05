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

package com.larsvansoest.runelite.clueitems.overlay;

/**
 * Includes Widget definitions for widgets used by the plugin.
 */
public enum Widget
{
	BANK(12, 10),

	BANK_EQUIPMENT(12, 69),

	BANK_EQUIPMENT_INVENTORY(15, 4),

	BANK_INVENTORY(15, 3),

	EQUIPMENT(387, 0),

	EQUIPMENT_EQUIPMENT(84, 1),

	EQUIPMENT_INVENTORY(85, 0),

	DEPOSIT_BOX(192, 2),

	GUIDE_PRICES(464, 2),

	GUIDE_PRICES_INVENTORY(238, 0),

	INVENTORY(149, 0),

	KEPT_ON_DEATH(4, 5),

	SHOP(300, 16),

	SHOP_INVENTORY(301, 0),

	GROUP_STORAGE(724, 10),

	GROUP_STORAGE_INVENTORY(725, 0),

	WATSON_NOTICE_BOARD(493, 0),
	WATSON_NOTICE_BOARD_BEGINNER(493, 4),
	WATSON_NOTICE_BOARD_EASY(493, 6),
	WATSON_NOTICE_BOARD_MEDIUM(493, 8),
	WATSON_NOTICE_BOARD_HARD(493, 10),
	WATSON_NOTICE_BOARD_ELITE(493, 12),
	WATSON_NOTICE_BOARD_MASTER(493, 14);

	/**
	 * id of the widget
	 */
	public final int id;

	/**
	 * group id of the widget, displayed in RuneLites widget inspector as groupId.childId;
	 */
	public final int groupId;

	/**
	 * child id of the widget, displayed in RuneLites widget inspector as groupId.childId;
	 */
	public final int childId;

	Widget(final int groupId, final int childId)
	{
		this.id = groupId << 16 | childId;
		this.groupId = groupId;
		this.childId = childId;
	}
}
