package game;

import java.util.ArrayList;
import java.util.Iterator;

import javax.sound.sampled.Clip;

// Represents a collection of BClips.
public class BClipSet implements Iterable<BClip> {
	
	/// Fields
	private ArrayList<BClip> clips;
	
	/// Constructors
	public BClipSet() {
		clips = new ArrayList<BClip>();
	}
	
	/// Methods
	public void start(int index) {
		clips.get(index).start();
	}
	
	public void stop(int index) {
		clips.get(index).stop();
	}
	
	public void stopAll() {
		for (var c : clips) {
			c.stop();
		}
	}
	
	public BClip get(int index) {
		return clips.get(index);
	}
	
	public void add(BClip clip) {
		clips.add(clip);
	}

	@Override
	public Iterator<BClip> iterator() {
		return clips.iterator();
	}
	
	
}
