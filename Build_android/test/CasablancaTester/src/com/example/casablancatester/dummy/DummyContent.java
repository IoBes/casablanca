package com.example.casablancatester.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.*;
import android.content.pm.*;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
    	/*for (String k : System.getProperties().stringPropertyNames()) {
    		addItem(new DummyItem(k, k + " === " + System.getProperties().getProperty(k)));
    	}*/
    	///addItem(new DummyItem("000", "FIRST = " + (new DummyContent()).bar()));
    	/*for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
    	    String key = entry.getKey();
    	    String value = entry.getValue();
    	    addItem(new DummyItem(key, key + " = " + value));
    	    // ...
    	}*/
    	
/*        addItem(new DummyItem("1", "Item " + (new DummyContent()).bar()));
        addItem(new DummyItem("XYZ123", "Item XYZ123"));
        addItem(new DummyItem("3", "Item 3"));
*/
    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
