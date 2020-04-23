package client.engine;

import java.util.LinkedList;

import client.engine.gfx.ImageRequest;

/**
 * Images that can be rendered have different z values, and one way to layer them up properly is to put them in a queue,
 * in the order to process them, and then the Renderer object will be able to render them accordingly.
 * 
 * @author 6177000
 *
 */
public class RenderQueue {
	private LinkedList<ImageRequest> renderQueue;
	
	public RenderQueue() {
		renderQueue = new LinkedList<>();
	}
	
	public void add(ImageRequest image) {
		if(renderQueue.size() == 0) {
			renderQueue.addFirst(image);
		}
		
		if(renderQueue.get(0).getzDepth() < image.getzDepth()) {
			renderQueue.addFirst(image);
			return;
		}
		
		for(int i=1;i<renderQueue.size();i++) {
			if(renderQueue.get(i).getzDepth() < image.getzDepth()) {
				renderQueue.add(i - 1, image);
				return;
			}
		}
	}
	
	public LinkedList<ImageRequest> getRenderQueue() {
		return renderQueue;
	}
	
	public void clear() {
		renderQueue.clear();
	}
}
