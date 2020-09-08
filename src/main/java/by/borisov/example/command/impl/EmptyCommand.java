package by.borisov.example.command.impl;

import by.borisov.example.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static by.borisov.example.command.PagePath.LOGIN;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return LOGIN;
    }
}
