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
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.swing.JPanel;

public class RequirementContainer extends JPanel
{
	private final GridBagConstraints c;
	private Collection<? extends RequirementPanel> requirementPanelCollection;

	public RequirementContainer(Collection<? extends RequirementPanel> requirementPanelCollection) {
		super(new GridBagLayout());
		this.c = new GridBagConstraints();
		this.c.fill = GridBagConstraints.HORIZONTAL;
		this.c.gridx = 0;
		this.c.gridy = 0;
		this.c.weightx = 1;
		this.load(requirementPanelCollection);
	}

	public void load(Collection<? extends RequirementPanel> requirementPanelCollection) {
		this.requirementPanelCollection = requirementPanelCollection;
		this.display(requirementPanelCollection.stream());
	}

	public void filter(Function<Object, Boolean> predicate) {
		this.display(this.requirementPanelCollection.stream().filter(predicate::apply));
	}

	private void display(Stream<? extends RequirementPanel> requirementPanels) {
		super.removeAll();
		this.c.gridy = 0;
		requirementPanels.forEach(requirementPanel -> {
			super.add(requirementPanel, this.c);
			this.c.gridy++;
		});
		super.revalidate();
		super.repaint();
	}
}
