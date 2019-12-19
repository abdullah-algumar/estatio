/*
 * Copyright 2012-2015 Eurocommercial Properties NV
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.estatio.module.application.imports.contributed;

import org.apache.isis.applib.annotation.*;

import org.isisaddons.module.excel.dom.ExcelService;

import org.estatio.module.application.imports.BudgetImportExportManager;
import org.estatio.module.budget.dom.budget.Budget;

// TODO: need to untangle this and push back down to budget module
@Mixin(method = "act")
public class Budget_importExportBudget {

    private final Budget budget;

    public Budget_importExportBudget(Budget budget) {
        this.budget = budget;
    }

    @Action(
            semantics = SemanticsOf.IDEMPOTENT,
            publishing = Publishing.DISABLED
    )
    @ActionLayout(contributed = Contributed.AS_ACTION)
    public BudgetImportExportManager act() {

        return new BudgetImportExportManager(budget);

    }

    @javax.inject.Inject
    private ExcelService excelService;

}
