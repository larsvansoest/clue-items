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

package com.larsvansoest.runelite.clueitems.ui.components;

import net.runelite.client.input.KeyListener;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.components.IconTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.*;
import java.util.function.Predicate;

/**
 * Lists {@link FoldablePanel} entries, provides functionality to display filtered sub-sets.
 *
 * @author Lars van Soest
 * @since 2.0.0
 */
public class DataGrid<T extends JPanel> extends JPanel
{
	private final List<T> entries;
	private final IconTextField searchBar;
	private final Map<String, CycleButton> filterButtons;
	private final Map<String, Predicate<T>> filters;
	private final EmoteClueItemsPalette palette;
	private Comparator<T> sort;
	private CycleButton sortButton;

	public DataGrid(final EmoteClueItemsPalette palette)
	{
		super(new GridBagLayout());
		this.entries = new ArrayList<>();

		this.palette = palette;
		this.searchBar = this.getSearchBar();
		this.filterButtons = new HashMap<>();
		this.filters = new HashMap<>();
		this.sort = null;
		this.sortButton = null;
		this.filters.put("_searchBar", panel -> panel.getName().toLowerCase().contains(this.searchBar.getText().toLowerCase()));
		this.paint();
	}

	private final void paint()
	{
		super.removeAll();
		final GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.weightx = 1;
		super.add(this.searchBar, c);
		c.weightx = 0;
		for (final CycleButton filterButton : this.filterButtons.values())
		{
			c.gridx++;
			super.add(filterButton, c);
		}
		this.entries.stream().sorted(this.sort).filter(e -> this.filters.values().stream().allMatch(p -> p.test(e))).forEach(entry ->
		{
			c.gridy++;
			super.add(entry, c);
		});
		super.revalidate();
		super.repaint();
	}

	public final void addSort(final Icon icon, final String toolTip, final Comparator<T> sort)
	{
		if (Objects.isNull(this.sortButton))
		{
			this.sortButton = new CycleButton(this.palette, icon, () -> this.sort = sort, toolTip);
		}
		else
		{
			this.sortButton.addOption(icon, () -> this.sort = sort, toolTip);
		}
		this.paint();
	}

	public final void addFilter(final String key, final Icon icon, final String toolTip, final Predicate<T> predicate)
	{
		if (this.filters.containsKey(key))
		{
			final CycleButton filterButton = this.filterButtons.get(key);
			filterButton.addOption(icon, () -> this.filters.put(key, predicate), toolTip);
		}
		else
		{
			final CycleButton filterButton = new CycleButton(this.palette, icon, () -> this.filters.put(key, predicate), toolTip);
			this.filterButtons.put(key, filterButton);
		}
		this.paint();
	}

	private IconTextField getSearchBar()
	{
		final IconTextField searchBar = new IconTextField();
		searchBar.setIcon(IconTextField.Icon.SEARCH);
		searchBar.setBackground(this.palette.getDefaultColor());
		searchBar.setHoverBackgroundColor(this.palette.getHoverColor());
		searchBar.setFont(FontManager.getRunescapeSmallFont());
		searchBar.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(final KeyEvent e)
			{
			}

			@Override
			public void keyPressed(final KeyEvent e)
			{
			}

			@Override
			public void keyReleased(final KeyEvent e)
			{
				DataGrid.this.paint();
			}
		});
		searchBar.addClearListener(this::paint);
		return searchBar;
	}

	public void load(final Collection<T> entries)
	{
		this.entries.clear();
		this.entries.addAll(entries);
		this.paint();
	}
}