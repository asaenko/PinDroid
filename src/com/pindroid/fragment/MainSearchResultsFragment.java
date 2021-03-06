/*
 * PinDroid - http://code.google.com/p/PinDroid/
 *
 * Copyright (C) 2010 Matt Schmidt
 *
 * PinDroid is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * PinDroid is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PinDroid; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

package com.pindroid.fragment;

import com.pindroid.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainSearchResultsFragment extends ListFragment {
	
	private OnSearchActionListener searchActionListener;
	
	public interface OnSearchActionListener {
		public void onBookmarkSearch();
		public void onTagSearch();
		public void onNoteSearch();
		public void onGlobalTagSearch();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		getActivity().setTitle(R.string.main_search_results_title);
		
		String[] MENU_ITEMS = new String[] {getString(R.string.search_results_bookmark),
				getString(R.string.search_results_tag), getString(R.string.search_results_note),
				getString(R.string.search_results_global_tag)};
		
		setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.main_view, MENU_ITEMS));
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    	if(position == 0){
		    		searchActionListener.onBookmarkSearch();
		    	} else if(position == 1){
		    		searchActionListener.onTagSearch();
		    	} else if(position == 2){
		    		searchActionListener.onNoteSearch();
		    	} else if(position == 3){
		    		searchActionListener.onGlobalTagSearch();
		    	}
		    }
		});
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_search_fragment, container, false);
    }
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			searchActionListener = (OnSearchActionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnSearchActionListener");
		}
	}
}