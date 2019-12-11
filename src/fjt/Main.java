package fjt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;

public class Main extends RecursiveAction {

  private Video video;

  private Main(Video video) {
    this.video = video;
  }

  private List<VideoClipAction> getClips() {
    List<VideoClipAction> subtasks = new ArrayList<>();

    int random = (int) (Math.random() * 10 + 1);
    System.out.println("Starting " + random + " random clips...\n");
    for (int i = 0; i < random; i++) {
      subtasks.add(new VideoClipAction(video, (int) (Math.random() * 1000 + 1)));
    }

    return subtasks;
  }

  @Override
  protected void compute() {
    if (video.getVideoStream().size() >= 1000) {
      ForkJoinTask.invokeAll(getClips());
    } else {
      System.out.println(Thread.currentThread() + " : No clipping possible.");
    }
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    Main m = new Main(new Video());
    Future f = ForkJoinPool.commonPool().submit(m);
    f.get();
  }
}
