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

package com.sun.faces.test.servlet30.composite;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
/**
 * Created by xinyuan.zhang on 5/5/17.
 */
public class Issue4204IT {

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
    public void testNullInDecorateTemplate() throws Exception {

        HtmlPage page = webClient.getPage(webUrl + "faces/issue4204.xhtml");
        HtmlSubmitInput button3 = (HtmlSubmitInput) page.getHtmlElementById("form:button_3").getFirstChild();
        page = button3.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.asText().contains("OutputText 3"));

        HtmlSubmitInput button2 = (HtmlSubmitInput) page.getHtmlElementById("form:button_2").getFirstChild();
        page = button2.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.asText().contains("OutputText 2"));

        HtmlSubmitInput button1 = (HtmlSubmitInput) page.getHtmlElementById("form:button_1").getFirstChild();
        page = button1.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.asText().contains("OutputText 1"));

        button3 = (HtmlSubmitInput) page.getHtmlElementById("form:button_3").getFirstChild();
        page = button3.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(!page.asText().contains("OutputText 3"));

        button2 = (HtmlSubmitInput) page.getHtmlElementById("form:button_2").getFirstChild();
        page = button2.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(!page.asText().contains("OutputText 2"));

        button1 = (HtmlSubmitInput) page.getHtmlElementById("form:button_1").getFirstChild();
        page = button1.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(!page.asText().contains("OutputText 1"));



        page = webClient.getPage(webUrl + "faces/issue4204_with_wrapper_fragments.xhtml");
        button3 = (HtmlSubmitInput) page.getHtmlElementById("form:button_3").getFirstChild();
        page = button3.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.asText().contains("OutputText 3"));

        button2 = (HtmlSubmitInput) page.getHtmlElementById("form:button_2").getFirstChild();
        page = button2.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.asText().contains("OutputText 2"));

        button1 = (HtmlSubmitInput) page.getHtmlElementById("form:button_1").getFirstChild();
        page = button1.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(page.asText().contains("OutputText 1"));

        button3 = (HtmlSubmitInput) page.getHtmlElementById("form:button_3").getFirstChild();
        page = button3.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(!page.asText().contains("OutputText 3"));

        button2 = (HtmlSubmitInput) page.getHtmlElementById("form:button_2").getFirstChild();
        page = button2.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(!page.asText().contains("OutputText 2"));

        button1 = (HtmlSubmitInput) page.getHtmlElementById("form:button_1").getFirstChild();
        page = button1.click();
        webClient.waitForBackgroundJavaScript(60000);
        assertTrue(!page.asText().contains("OutputText 1"));

    }
}
