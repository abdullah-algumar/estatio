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
package org.estatio.module.invoice.dom;

import javax.jdo.annotations.DiscriminatorStrategy;

import org.apache.isis.applib.annotation.DomainObject;

import org.isisaddons.module.security.dom.tenancy.ApplicationTenancy;

@DomainObject(
        objectType = "org.estatio.module.invoice.dom.InvoiceItemForTesting"
)
@javax.jdo.annotations.Discriminator(
        strategy = DiscriminatorStrategy.VALUE_MAP,
        column = "discriminator",
        value = "org.estatio.module.invoice.dom.InvoiceItemForTesting"
)
public class InvoiceItemForTesting extends InvoiceItem {

    public InvoiceItemForTesting(final Invoice invoice) {
        super(invoice);
    }

    public ApplicationTenancy getApplicationTenancy() {
        return null;
    }

}
