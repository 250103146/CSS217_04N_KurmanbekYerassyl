package CSS217;

interface Image {
    void show();
}
class RealImage implements Image {
    private final String fileName;
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }
    private void loadFromDisk() {
        System.out.println("--> [SLOW] Reading " + fileName + " from hard drive...");
    }
    @Override
    public void show() {
        System.out.println("Displaying " + fileName + " on screen!");
    }
}
class ProxyImage implements Image {
    private final String fileName;
    private final String userRole;
    private RealImage realImage;
    public ProxyImage(String fileName, String userRole) {
        this.fileName = fileName;
        this.userRole = userRole;
    }
    @Override
    public void show() {
        if (!"ADMIN".equals(userRole)) {
            System.out.println("[ACCESS DENIED] You lack permission to view: " + fileName);
            return;
        }
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.show();
    }
}
interface Video {
    void play();
}

class RealVideo implements Video {
    private final String title;

    public RealVideo(String title) {
        this.title = title;
        System.out.println("--> [DOWNLOADING] Connecting to server & buffering 4K video: " + title);
    }

    @Override
    public void play() {
        System.out.println("Playing video: " + title);
    }
}

class ProxyVideo implements Video {
    private final String title;
    private RealVideo realVideo;
    private int viewCount = 0;

    public ProxyVideo(String title) {
        this.title = title;
    }

    @Override
    public void play() {
        viewCount++;
        System.out.println("--> [AUDIT] Video has been played " + viewCount + " time(s).");
        if (realVideo == null) {
            realVideo = new RealVideo(title);
        }
        realVideo.play();
    }
}

interface Internet {
    void connectTo(String site);
}

class RealInternet implements Internet {
    @Override
    public void connectTo(String site) {
        System.out.println("Connected to site: " + site);
    }
}

class SchoolProxyInternet implements Internet {
    private RealInternet realInternet;

    @Override
    public void connectTo(String site) {
        if (site.equals("gaming.com") || site.equals("socialmedia.com")) {
            System.out.println("[BLOCKED BY FIREWALL] Cannot access: " + site);
            return;
        }
        if (realInternet == null) {
            realInternet = new RealInternet();
        }
        realInternet.connectTo(site);
    }
}
public class Proxy {
    public static void main(String[] args) {
        System.out.println("=== Task 1 ===");
        Image guest = new ProxyImage("salary_sheet.png", "GUEST");
        Image admin = new ProxyImage("salary_sheet.png", "ADMIN");
        guest.show();
        admin.show();

        System.out.println("\n=== Task 2 ===");
        Video video = new ProxyVideo("Cat_Memes.mp4");
        video.play();
        video.play();
        video.play();

        System.out.println("\n=== Task 3 ===");
        Internet internet = new SchoolProxyInternet();
        internet.connectTo("wikipedia.org");
        internet.connectTo("gaming.com");
    }
}
