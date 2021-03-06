/*
 * Copyright (c) 2018, Psikoi <https://github.com/psikoi>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import lombok.Setter;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.components.shadowlabel.JShadowedLabel;

/**
 * A progress bar to be displayed underneath the GE offer item panels
 */
public class ProgressBar extends JPanel
{
	@Setter
	private int maximumValue;

	@Setter
	private int value;

	private final JLabel leftLabel = new JShadowedLabel();
	private final JLabel rightLabel = new JShadowedLabel();
	private final JLabel centerLabel = new JShadowedLabel();

	public ProgressBar()
	{
		setLayout(new BorderLayout());
		setBackground(Color.GREEN.darker());
		setPreferredSize(new Dimension(100, 16));

		leftLabel.setFont(FontManager.getRunescapeSmallFont());
		leftLabel.setForeground(Color.WHITE);
		leftLabel.setBorder(new EmptyBorder(2, 5, 0, 0));

		rightLabel.setFont(FontManager.getRunescapeSmallFont());
		rightLabel.setForeground(Color.WHITE);
		rightLabel.setBorder(new EmptyBorder(2, 0, 0, 5));

		centerLabel.setFont(FontManager.getRunescapeSmallFont());
		centerLabel.setForeground(Color.WHITE);
		centerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		centerLabel.setBorder(new EmptyBorder(2, 0, 0, 0));

		add(leftLabel, BorderLayout.WEST);
		add(centerLabel, BorderLayout.CENTER);
		add(rightLabel, BorderLayout.EAST);

	}

	@Override
	public void paint(Graphics g)
	{
		double percentage = getPercentage();
		int topWidth = (int) (getSize().width * (percentage / 100));

		super.paint(g);
		g.setColor(getForeground());
		g.fillRect(0, 0, topWidth, 16);
		super.paintComponents(g);
	}


	public void setLeftLabel(String txt)
	{
		this.leftLabel.setText(txt);
	}

	public void setRightLabel(String txt)
	{
		this.rightLabel.setText(txt);
	}

	public void setCenterLabel(String txt)
	{
		this.centerLabel.setText(txt);
	}

	public double getPercentage()
	{
		if (value == 0)
		{
			return 0;
		}

		return (value * 100) / maximumValue;
	}
}