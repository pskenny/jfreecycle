package io.github.pskenny.jfreecycle;

import java.util.Collection;

import io.github.pskenny.libjfreecycle.model.Post;

/**
 * Freecycle commandline utility. Super duper fragile to changes on Freecycle.
 * G'luck.
 */
public class App {
    public enum Type {WANTED, OFFER, ALL}

    public App(String groupId) {
        Collection<Post> posts = io.github.pskenny.libjfreecycle.util.PostUtil.getPosts(groupId, Post.Type.OFFER);
        posts.forEach(System.out::println);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: jfreecycle [GroupID]\n    Example: jfreecycle GalwayIE");
            System.exit(1);
        }

        new App(args[0]);
    }
}
