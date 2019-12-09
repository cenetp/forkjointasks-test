package fjt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Video {
  private List<String> videoStream;

  // create fake video stream
  List<String> getVideoStream() {
    return IntStream.range(0, 1_000_000).mapToObj(String::valueOf).collect(Collectors.toList());
  }
}
