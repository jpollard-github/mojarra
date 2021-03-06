/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.faces.test.servlet30.listener;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class ActionListenerImplIT {

    private String webUrl;
    private WebClient webClient;

    @Before
    public void setUp() {
        webUrl = System.getProperty("integration.url");
        webClient = new WebClient();
    }

    @After
    public void tearDown() {
        webClient.close();
    }

    @Test
    public void testProcessAction() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "/faces/login.xhtml");

        System.out.println("/n" + page.asXml() + "/n");

        HtmlSubmitInput button = (HtmlSubmitInput) page.getHtmlElementById("form:submit");
        page = (HtmlPage) button.click();

        System.out.println("/n" + page.asXml() + "/n");

        assertTrue(page.asText().contains("VIEW ID IS: /must-login-first.xhtml"));

        page = webClient.getPage(webUrl + "/faces/login.xhtml");
        button = (HtmlSubmitInput) page.getHtmlElementById("form:submit1");
        page = (HtmlPage) button.click();
        assertTrue(page.asText().contains("VIEW ID IS: /home.xhtml"));
    }

    @Test
    public void testIllegalArgException() throws Exception {
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page = webClient.getPage(webUrl + "/faces/login.xhtml");
        HtmlSubmitInput button = (HtmlSubmitInput) page.getHtmlElementById("form:submit2");
        TextPage page1 = button.click();
        assertTrue(page1.getContent().contains("MethodNotFoundException"));
    }
}
