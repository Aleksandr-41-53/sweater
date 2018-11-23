package com.darthjaxx.sweater.domain.util;

import com.darthjaxx.sweater.domain.User;

public abstract class MessageHelper {
    public static String getAuthorName(User author) {
        return author != null ? author.getUsername() : "<none>";
    }
}
