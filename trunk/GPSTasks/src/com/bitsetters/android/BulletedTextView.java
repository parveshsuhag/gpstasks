/* $Id: BulletedTextView.java 57 2007-11-21 18:31:52Z steven $
 * 
 * Copyright 2007 Steven Osborn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bitsetters.android;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BulletedTextView extends LinearLayout {
	
	private TextView mText;
	private ImageView mBullet;
	
	public BulletedTextView(Context context, String text, Drawable bullet) {
		super(context);

		this.setOrientation(HORIZONTAL);

		mBullet = new ImageView(context);
		mBullet.setImageDrawable(bullet);
		// left, top, right, bottom
		mBullet.setPadding(0, 2, 5, 0);
		addView(mBullet,  new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		mText = new TextView(context);
		mText.setText(text);
		addView(mText, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	}

	public void setText(String words) {
		mText.setText(words);
	}
	
	public void setBullet(Drawable bullet) {
		mBullet.setImageDrawable(bullet);
	}

}