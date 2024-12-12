import java.util.*;

class Video {
    String title;
    String url;
    int views;
    int likes;
    int dislikes;
    Set<Comment> comments;

    public Video(String title, String url, int views, int likes, int dislikes, Set<Comment> comments) {
        this.title = title;
        this.url = url;
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
    }
}

class Comment {
    String text;
    int likes;
    int dislikes;

    public Comment(String text, int likes, int dislikes) {
        this.text = text;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Comment comment = (Comment) obj;
        return likes == comment.likes &&
                dislikes == comment.dislikes &&
                Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, likes, dislikes);
    }
}

class Videoblog {
    String bloggerName;
    Set<Video> videos;

    public Videoblog(String bloggerName, Set<Video> videos) {
        this.bloggerName = bloggerName;
        this.videos = videos;
    }
}

public class Main {
    public static void main(String[] args) {
        // Ініціалізація даних всередині методу main
        Set<Comment> comments1 = new HashSet<>(
                Arrays.asList(
                        new Comment("Great explanation!", 30, 0),
                        new Comment("Needs more examples", 15, 3)
                )
        );
        Set<Comment> comments2 = new HashSet<>(
                Arrays.asList(
                        new Comment("Clear and concise", 20, 1),
                        new Comment("I didn't understand this part", 8, 12)
                )
        );
        Set<Comment> comments3 = new HashSet<>(
                Arrays.asList(
                        new Comment("Super helpful, thanks!", 40, 0),
                        new Comment("Not what I was looking for", 2, 25)
                )
        );

        Video video1 = new Video("UEFA Champions League Anthem", "https://www.youtube.com/watch?v=xw7Z3wUV2CU&ab_channel=UEFA", 1000000, 300, 25, comments1);
        Video video2 = new Video("KYLIAN MBAPPÉ: EVERY Champions League Goal", "https://www.youtube.com/watch?v=xrT4R5C1mBY&ab_channel=UEFA", 5000, 400, 25, comments2);
        Video video3 = new Video("CRISTIANO RONALDO: ALL #UCL GOALS!", "https://www.youtube.com/watch?v=UK5cu3LJ9qk&ab_channel=UEFA", 10000000, 500, 10, comments3);

        Set<Video> videos = new HashSet<>(Arrays.asList(video1, video2, video3));
        Videoblog blog = new Videoblog("UEFA", videos);

        // Загальна кількість переглядів
        int totalViews = 0;
        for (Video video : blog.videos) {
            totalViews += video.views;
        }
        System.out.println("Загальна кількість переглядів: " + totalViews);

        // Відео з найбільшою кількістю дизлайків
        List<Video> mostDislikedVideos = new ArrayList<>();
        int maxDislikes = 0;
        for (Video video : blog.videos) {
            if (video.dislikes > maxDislikes) {
                maxDislikes = video.dislikes;
                mostDislikedVideos.clear();
                mostDislikedVideos.add(video);
            } else if (video.dislikes == maxDislikes) {
                mostDislikedVideos.add(video);
            }
        }

        System.out.println("Відео з найбільшою кількістю дизлайків:");
        if (mostDislikedVideos.isEmpty()) {
            System.out.println("Немає таких відео.");
        } else {
            for (Video video : mostDislikedVideos) {
                System.out.println(video.title + " (" + video.url + ")");
            }
        }
    }
}
