/* $Id: BulletedText.java 57 2007-11-21 18:31:52Z steven $
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

import android.graphics.drawable.Drawable;

/**
 * 
 * @author Steven Osborn - http://steven.bitsetters.com
 */
public class BulletedText extends Object {
    
	private String mText = "";
	private Drawable mBullet;
	private boolean mSelectable = true;

	/**
	 * 
	 * @param text
	 * @param bullet
	 */
	public BulletedText(String text, Drawable bullet) {
		mBullet = bullet;
		mText = text;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isSelectable() {
		return mSelectable;
	}
	
	/**
	 * 
	 * @param selectable
	 */
	public void setSelectable(boolean selectable) {
		mSelectable = selectable;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getText() {
		return mText;
	}
	
	/**
	 * 
	 * @param text
	 */
	public void setText(String text) {
		mText = text;
	}
	
	/**
	 * 
	 * @param bullet
	 */
	public void setBullet(Drawable bullet) {
		mBullet = bullet;
	}
	
	/**
	 * 
	 * @return
	 */
	public Drawable getBullet() {
		return mBullet;
	}
}
