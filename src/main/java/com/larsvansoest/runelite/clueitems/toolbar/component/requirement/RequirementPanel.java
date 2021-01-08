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

package com.larsvansoest.runelite.clueitems.toolbar.component.requirement;

import com.larsvansoest.runelite.clueitems.data.RequirementStatus;
import com.larsvansoest.runelite.clueitems.toolbar.component.requirement.header.RequirementPanelHeader;
import com.larsvansoest.runelite.clueitems.toolbar.palette.EmoteClueItemsPanelPalette;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class RequirementPanel extends UpdatablePanel
{
	private final RequirementPanelHeader requirementPanelHeader;
	private final JPanel foldContent;
	private final GridBagConstraints foldConstraints;
	private final Map<String, Object[]> filterables;
	private Boolean expanded;

	public RequirementPanel(EmoteClueItemsPanelPalette emoteClueItemsPanelPalette, String name) {
		super.setLayout(new GridBagLayout());
		super.setBackground(emoteClueItemsPanelPalette.getDefaultColor());
		super.setName(name);

		this.expanded = false;
		this.requirementPanelHeader = new RequirementPanelHeader(this, emoteClueItemsPanelPalette, new Dimension(155, 20), name);

		this.foldContent = new JPanel(new GridBagLayout());
		this.foldContent.setBackground(emoteClueItemsPanelPalette.getFoldContentColor());
		this.foldConstraints = new GridBagConstraints();
		this.filterables = new HashMap<>();

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		super.add(this.requirementPanelHeader, c);

		c.gridy++;
		this.foldContent.setVisible(this.expanded);
		super.add(this.foldContent, c);
	}

	public final void setStatus(RequirementStatus status) {
		this.requirementPanelHeader.getNameLabel().setForeground(status.colour);
		this.setFilterable("status", status);
	}

	public final Boolean fold() {
		this.expanded = !this.expanded;
		this.foldContent.setVisible(this.expanded);
		return this.expanded;
	}

	public final void setFilterable(String key, Object... values) {
		this.filterables.put(key, values);
	}

	public final Object[] getFilterable(String key) {
		return this.filterables.get(key);
	}

	public final void addChild(JPanel content) {
		this.foldContent.add(content, this.foldConstraints);
		this.foldConstraints.gridy++;
	}

	public final void addIcon(JLabel icon) {
		this.requirementPanelHeader.addIcon(icon);
	}

	public final Boolean isExpanded()
	{
		return this.expanded;
	}
}