/*
 *
 *  Copyright 2012-2013 Eurocommercial Properties NV
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
package org.estatio.fixturescripts;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.LocalDate;

import org.apache.isis.core.commons.exceptions.IsisApplicationException;

import org.estatio.dom.lease.LeaseTerm;
import org.estatio.dom.lease.LeaseTermForIndexableRent;
import org.estatio.dom.lease.LeaseTerms;

public class FixLeaseTerms implements Callable<Object> {

    @Override
    public Object call() throws Exception {
        int countEffectiveDate = 0;
        int countBaseStartDate = 0;
        for (LeaseTerm term : leaseTerms.allLeaseTerms()) {
            if (term instanceof LeaseTermForIndexableRent) {
                if (fixEffectiveDate((LeaseTermForIndexableRent) term)) {
                    countEffectiveDate++;
                }
                if (fixBaseIndexStartDate((LeaseTermForIndexableRent) term)) {
                    countBaseStartDate++;
                }
            }
        }
        return String.format("%d effective dates fixed, %d base index dates fixed", countEffectiveDate, countBaseStartDate);
    }

    private boolean fixBaseIndexStartDate(LeaseTermForIndexableRent term) {
        try {
            if (term.getNext() == null) {
                LeaseTermForIndexableRent previous = (LeaseTermForIndexableRent) term.getPrevious();
                if (previous != null) {
                    LocalDate nextIndexStartDate = previous.getNextIndexStartDate();
                    if (nextIndexStartDate != null && (term.getBaseIndexStartDate() == null || term.getBaseIndexStartDate().compareTo(nextIndexStartDate) != 0)) {
                        term.setBaseIndexStartDate(nextIndexStartDate);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            throw new IsisApplicationException();
        }
        return false;
    }

    private boolean fixEffectiveDate(LeaseTermForIndexableRent term) {
        LocalDate indexAvailableDate = term.getNextIndexStartDate() == null ? null : term.getNextIndexStartDate().plusMonths(2).plusDays(16);
        LocalDate effectiveDate = null;
        if (indexAvailableDate != null 
                && indexAvailableDate.compareTo(term.getStartDate()) > 0 
                && term.getSettledValue() == null) {
            effectiveDate = term.getLeaseItem().getInvoicingFrequency().getNextDueDate(indexAvailableDate);
        }
        if (!ObjectUtils.equals(effectiveDate, term.getEffectiveDate())){
            term.setEffectiveDate(effectiveDate);
            return true;
        }
        return false;
    }

    private LeaseTerms leaseTerms;

    public void injectLeaseTerms(LeaseTerms leaseTerms) {
        this.leaseTerms = leaseTerms;
    }

}
