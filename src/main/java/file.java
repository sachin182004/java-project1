import java.util.*;

public class InstagramApp {

    static class Post {
        String content;
        int likes = 0;

        Post(String content) {
            this.content = content;
        }

        void like() {
            likes++;
        }

        void show(String user) {
            System.out.println(user + " -> " + content + " | Likes: " + likes);
        }
    }

    static class User {
        String username, password;
        List<Post> posts = new ArrayList<>();
        List<User> following = new ArrayList<>();

        User(String u, String p) {
            username = u;
            password = p;
        }

        void addPost(String text) {
            posts.add(new Post(text));
        }

        void follow(User u) {
            if (!following.contains(u) && u != this) {
                following.add(u);
            }
        }

        void showFeed() {
            System.out.println("\n--- YOUR FEED ---");
            for (User u : following) {
                for (Post p : u.posts) {
                    p.show(u.username);
                }
            }
        }
    }

    static Scanner sc = new Scanner(System.in);
    static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Signup\n2. Login\n3. Exit");
            int ch = sc.nextInt();

            if (ch == 1) signup();
            else if (ch == 2) login();
            else {
                System.out.println("Bye 👋");
                break;
            }
        }
    }

    static void signup() {
        System.out.print("Username: ");
        String u = sc.next();
        System.out.print("Password: ");
        String p = sc.next();
        users.add(new User(u, p));
        System.out.println("Account created!");
    }

    static void login() {
        System.out.print("Username: ");
        String u = sc.next();
        System.out.print("Password: ");
        String p = sc.next();

        for (User user : users) {
            if (user.username.equals(u) && user.password.equals(p)) {
                dashboard(user);
                return;
            }
        }
        System.out.println("Invalid login!");
    }

    static void dashboard(User user) {
        while (true) {
            System.out.println("\n1. Post\n2. Follow\n3. Feed\n4. Like\n5. Logout");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Enter post: ");
                sc.nextLine();
                user.addPost(sc.nextLine());
            }

            else if (ch == 2) {
                System.out.print("Follow username: ");
                String name = sc.next();
                for (User u : users) {
                    if (u.username.equals(name)) {
                        user.follow(u);
                        System.out.println("Now following " + name);
                    }
                }
            }

            else if (ch == 3) {
                user.showFeed();
            }

            else if (ch == 4) {
                System.out.print("Like whose post? ");
                String name = sc.next();
                for (User u : users) {
                    if (u.username.equals(name) && !u.posts.isEmpty()) {
                        u.posts.get(0).like();
                        System.out.println("Liked!");
                    }
                }
            }

            else {
                System.out.println("Logged out.");
                break;
            }
        }
    }
}

