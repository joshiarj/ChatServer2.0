/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arj.chatserver2.command;

import java.util.HashMap;

/**
 *
 * @author Zeppelin
 */
public class ChatCommandFactory {

    public static HashMap<String, ChatCommand> commands = initCommands();

    public static HashMap<String, ChatCommand> initCommands() {
        HashMap<String, ChatCommand> cmds = new HashMap<>();
        cmds.put("LIST", new ListCommand());
        cmds.put("EXIT", new ExitCommand());
        cmds.put("BLOCK", new BlockCommand());
        cmds.put("UNBLOCK", new UnblockCommand());
        cmds.put("BLOCKLIST", new BlockListCommand());
        cmds.put("PM", new PMCommand());
        cmds.put("MSG", new MsgCommand());
        cmds.put("STATUS", new StatusCommand());
        cmds.put("INBOX", new InboxCommand());
        return cmds;
    }

    public static ChatCommand get(String param) {
        if (commands.containsKey(param)) {
            return commands.get(param);
        }
        return null;
    }

}
