/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.kylin.common.util;

import java.lang.reflect.AccessibleObject;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.kylin.common.SystemPropertiesCache;

/**
 * Contains methods that call JDK methods that the
 * <a href="https://github.com/policeman-tools/forbidden-apis">forbidden
 * APIs checker</a> does not approve of.
 *
 * <p>This class is excluded from the check, so methods called via this class
 * will not fail the build.
 */
public class Unsafe {

    private Unsafe() {
    }

    /** Calls {@link System#exit}. */
    public static void systemExit(int status) {
        System.exit(status);
    }

    /** For {@link MessageFormat#format(String, Object...)} cannot set locale*/
    public static String format(Locale locale, String pattern, Object... arguments) {
        MessageFormat temp = new MessageFormat(pattern, locale);
        return temp.format(arguments);
    }

    /** Reflection usage to work around access flags fails with SecurityManagers
     * and likely will not work anymore on runtime classes in Java 9 */
    public static void changeAccessibleObject(AccessibleObject accessibleObject, boolean value) {
        accessibleObject.setAccessible(value);
    }

    /** Overwrite system property in test */
    public static void overwriteSystemProp(Map<String, String> systemProp, String key, String value) {
        if (systemProp != null) {
            systemProp.put(key, System.getProperty(key));
        }

        if (StringUtils.isEmpty(value)) {
            SystemPropertiesCache.clearProperty(key);
        } else {
            SystemPropertiesCache.setProperty(key, value);
        }
    }

    /** Restore all system properties in test */
    public static void restoreAllSystemProp(Map<String, String> systemProp) {
        if (systemProp != null) {
            systemProp.forEach((prop, value) -> SystemPropertiesCache.clearProperty(prop));
            systemProp.clear();
        }
    }

    /** Set system property */
    public static String setProperty(String property, String value) {
        return SystemPropertiesCache.setProperty(property, value);
    }

    /** Clear system property */
    public static void clearProperty(String property) {
        SystemPropertiesCache.clearProperty(property);
    }
}
