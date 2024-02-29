package me.q1zz.combatlog.message;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Getter
@Setter
public class Message {

    private MessageType type;
    private String text;

    public Message(@NotNull MessageType type, @NotNull String text) {
        this.type = type;
        this.text = text;
    }

    public Message(@NotNull Message message) {
        this.type = message.type;
        this.text = message.text;
    }

    @NotNull
    public Message replaceAll(Map<String, String> replacements) {
        replacements.forEach((key, value) -> this.setText(this.text.replace(key, value)));
        return this;
    }

    @NotNull
    public static Message chat(String... text) {
        return new Message(MessageType.CHAT, String.join("\n", text));
    }

    @NotNull
    public static Message title(String text) {
        return new Message(MessageType.TITLE, text);
    }

    @NotNull
    public static Message subtitle(String text) {
        return new Message(MessageType.SUBTITLE, text);
    }

    @NotNull
    public static Message actionbar(String text) {
        return new Message(MessageType.ACTIONBAR, text);
    }

    @NotNull
    public Message clone() {
        return new Message(this);
    }

}
