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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import javax.swing.JPanel;

public class RequirementContainer extends JPanel
{
	private final GridBagConstraints c;
	private final Map<String, Object> filterables;
	private RequirementPanel expandedPanel;
	private List<? extends RequirementPanel> requirementPanels;

	public RequirementContainer()
	{
		super(new GridBagLayout());
		this.filterables = new HashMap<>();
		this.expandedPanel = null;

		this.c = new GridBagConstraints();
		this.c.fill = GridBagConstraints.HORIZONTAL;
		this.c.gridx = 0;
		this.c.weightx = 1;
	}

	public void load(Collection<? extends RequirementPanel> requirementPanelCollection)
	{
		this.requirementPanels = new ArrayList<>(requirementPanelCollection);
		this.requirementPanels.sort(Comparator.comparing(RequirementPanel::getName));
		this.display(this.requirementPanels.stream());
	}

	public void toggleFold(RequirementPanel requirementPanel) {
		RequirementPanel previous = this.expandedPanel;
		if(this.expandedPanel != null) {
			this.expandedPanel.fold();
			this.expandedPanel = null;
		}
		if(previous != requirementPanel)
		{
			requirementPanel.unfold();
			this.expandedPanel = requirementPanel;
		}
		super.revalidate();
		super.repaint();
	}

	public void addFilter(String key, Object value)
	{
		this.filterables.put(key, value);
	}

	public void runFilters()
	{
		this.display(
			this.requirementPanels.stream().filter(requirementPanel ->
				this.filterables.entrySet().stream().allMatch(entry -> {
					Object filterValue = entry.getValue();
					Object[] requirementValue = requirementPanel.getFilterable(entry.getKey());
					return filterValue == null || requirementValue == null || Arrays.asList(requirementValue).contains(filterValue);
				})));
	}

	private void display(Stream<? extends RequirementPanel> requirementPanels)
	{
		super.removeAll();
		this.c.gridy = 0;
		requirementPanels.forEachOrdered(requirementPanel -> {
			super.add(requirementPanel, this.c);
			this.c.gridy++;
		});
		super.revalidate();
		super.repaint();
	}
}
