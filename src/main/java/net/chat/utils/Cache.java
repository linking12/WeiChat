/*
 * EarnstoneUtils: Earnstone Utilities.
 * 
 * Copyright 2010 Corey Hulen, Earnstone Corporation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package net.chat.utils;

import java.util.ArrayList;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.LRUMap;

public class Cache<K, T> {

	private long timeToLiveInMillis;

	private LRUMap cacheMap;

	protected class CachedObject {
		public long lastAccessed = System.currentTimeMillis();
		public T value;

		protected CachedObject(T value) {
			this.value = value;
		}
	}

	public Cache(int maxItems) {
		this(0, 0, maxItems);
	}

	public Cache(long timeToLiveInSeconds, final long timerIntervalInSeconds,
			int maxItems) {
		this.timeToLiveInMillis = timeToLiveInSeconds * 1000;

		cacheMap = new LRUMap(maxItems);

		if (timeToLiveInMillis > 0 && timerIntervalInSeconds > 0) {

			Thread t = new Thread(new Runnable() {
				public void run() {
					while (true) {
						try {
							Thread.sleep(timerIntervalInSeconds * 1000);
						} catch (InterruptedException ex) {
						}

						cleanup();
					}
				}
			});

			t.setDaemon(true);
			t.start();
		}
	}

	public void put(K key, T value) {
		synchronized (cacheMap) {
			cacheMap.put(key, new CachedObject(value));
		}
	}

	public T get(K key) {
		synchronized (cacheMap) {
			@SuppressWarnings("unchecked")
			CachedObject c = (CachedObject) cacheMap.get(key);

			if (c == null)
				return null;
			else {
				c.lastAccessed = System.currentTimeMillis();
				return c.value;
			}
		}
	}

	public void remove(K key) {
		synchronized (cacheMap) {
			cacheMap.remove(key);
		}
	}

	public int size() {
		synchronized (cacheMap) {
			return cacheMap.size();
		}
	}

	@SuppressWarnings("unchecked")
	public void cleanup() {

		long now = System.currentTimeMillis();
		ArrayList<K> keysToDelete = null;

		synchronized (cacheMap) {
			MapIterator itr = cacheMap.mapIterator();

			keysToDelete = new ArrayList<K>((cacheMap.size() / 2) + 1);
			K key = null;
			CachedObject c = null;

			while (itr.hasNext()) {
				key = (K) itr.next();
				c = (CachedObject) itr.getValue();

				if (c != null && (now > (timeToLiveInMillis + c.lastAccessed))) {
					keysToDelete.add(key);
				}
			}
		}

		for (K key : keysToDelete) {
			synchronized (cacheMap) {
				cacheMap.remove(key);
			}

			Thread.yield();
		}
	}
}
