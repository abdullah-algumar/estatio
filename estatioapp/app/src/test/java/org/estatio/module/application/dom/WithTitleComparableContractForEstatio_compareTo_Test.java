/*
 *
 *  Copyright 2012-2014 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.estatio.module.application.dom;

import com.google.common.collect.ImmutableMap;

import org.incode.module.base.dom.with.WithTitleComparable;

/**
 * Automatically tests all domain objects implementing {@link WithTitleComparable}.
 */
public class WithTitleComparableContractForEstatio_compareTo_Test extends
        org.incode.module.base.dom.with.ComparableByTitleContractTestAbstract_compareTo {

    public WithTitleComparableContractForEstatio_compareTo_Test() {
        super("org.estatio", ImmutableMap.<Class<?>,Class<?>>of());
    }

}
