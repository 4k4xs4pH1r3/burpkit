/*
 * BurpKit - WebKit-based penetration testing plugin for BurpSuite
 * Copyright (C) 2015  Red Canari, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.redcanari.js.proxies;

import burp.BurpExtender;
import burp.IMessageEditorController;
import burp.IMessageEditorTab;
import burp.IMessageEditorTabFactory;
import com.redcanari.js.Helpers;
import com.redcanari.js.wrappers.TextEditorWrapper;
import netscape.javascript.JSObject;

/**
 * Created by ndouba on 14-12-09.
 */
public class MessageEditorTabFactoryJSProxy extends JSProxy implements IMessageEditorTabFactory{

    public MessageEditorTabFactoryJSProxy(JSObject jsObject) {
        super(jsObject);
    }

    @Override
    public IMessageEditorTab createNewInstance(IMessageEditorController controller, boolean editable) {
        return Helpers.<IMessageEditorTab>wrapInterface(
                call(
                        "createNewInstance",
                        controller,
                        editable,
                        new TextEditorWrapper(BurpExtender.getBurpExtenderCallbacks().createTextEditor())
                ),
                MessageEditorTabJSProxy.class
        );
    }
}
