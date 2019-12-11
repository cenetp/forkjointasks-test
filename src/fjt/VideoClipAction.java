package fjt;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class VideoClipAction extends RecursiveTask {
  private int length;
  private List<String> videoStream;

  VideoClipAction(Video video, int length) {
    this.length = length;
    this.videoStream = video.getVideoStream();
  }

  @Override
  protected Object compute() {
    List<String> clip;
    // create a clip with the given length
    try {
      clip = videoStream.subList(length, length + 1000);
    } catch (Exception e) {
      clip = videoStream.subList(length, videoStream.size() - 1);
    }
    System.out.println(Thread.currentThread() +
        "\n\tVideo clip from: " + clip.get(0) + " to " + clip.get(clip.size() - 1) + "\n");
    return null;
  }
}
