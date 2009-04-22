/* $Id: BulletedTextListAdapter.java 57 2007-11-21 18:31:52Z steven $
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

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


/**
 * 
 * @author Steven Osborn - http://steven.bitsetters.com
 */
public class BulletedTextListAdapter extends BaseAdapter {

    /**
     * Remember our context so we can use it when constructing views.
     */
    private Context mContext;

    private List<BulletedText> mItems;

    /**
     * 
     * @param context
     */
    public BulletedTextListAdapter(Context context) {
	mContext = context;
	mItems  = new ArrayList<BulletedText>();
    }

    /**
     * 
     * @param bt
     */
    public void addItem(BulletedText bt) {
	mItems.add(bt);
    }

    /**
     * 
     * @param bti
     */
    public void setListItems(List<BulletedText> bti) {
	mItems = bti;
    }

    /**
     * The number of items in the
     */
    public int getCount() {
	return mItems.size();
    }

    /**
     * 
     */
    public Object getItem(int position) {
	return mItems.get(position);
    }

    /**
     * 
     */
    public boolean areAllItemsSelectable() {
	return false;
    }

    /**
     * 
     */
    public boolean isSelectable(int position) {
	return mItems.get(position).isSelectable();
    }

    /**
     * Use the array index as a unique id.
     */
    public long getItemId(int position) {
	return position;
    }

    /**
     * Make a BulletedTextView to hold each row.
     */
    public View getView(int position, View convertView, ViewGroup parent) {
	BulletedTextView btv;
	if (convertView == null) {
	    btv = new BulletedTextView(mContext, mItems.get(position).getText(),
		    mItems.get(position).getBullet());
	} else {
	    btv = (BulletedTextView) convertView;
	    btv.setText(mItems.get(position).getText());
	    btv.setBullet(mItems.get(position).getBullet());
	}

	return btv;
    }
}