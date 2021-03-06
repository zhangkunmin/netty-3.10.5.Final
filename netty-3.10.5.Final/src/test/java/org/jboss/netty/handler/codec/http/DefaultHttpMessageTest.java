/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.jboss.netty.handler.codec.http;

import org.junit.Assert;
import org.junit.Test;

public class DefaultHttpMessageTest {

    @Test
    public void testHeaderRemoval() {
        HttpMessage m = new DefaultHttpRequest(
                HttpVersion.HTTP_1_1, HttpMethod.GET, "/");

        // Insert sample keys.
        for (int i = 0; i < 1000; i ++) {
            m.headers().set(String.valueOf(i), "");
        }

        // Remove in reversed order.
        for (int i = 999; i >= 0; i --) {
            m.headers().remove(String.valueOf(i));
        }

        // Check if random access returns nothing.
        for (int i = 0; i < 1000; i ++) {
            Assert.assertNull(m.headers().get(String.valueOf(i)));
        }

        // Check if sequential access returns nothing.
        Assert.assertTrue(m.headers().entries().isEmpty());
    }
}
