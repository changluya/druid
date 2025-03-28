/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.sql.dialect.postgresql.ast.stmt;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.ast.statement.SQLDropCatalogStatement;
import com.alibaba.druid.sql.dialect.postgresql.visitor.PGASTVisitor;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

public class PGDropDatabaseStatement extends SQLDropCatalogStatement implements PGSQLStatement {
    private boolean usingWith;
    private boolean force;

    public PGDropDatabaseStatement(DbType dbType) {
        super(dbType);
    }

    public boolean isUsingWith() {
        return usingWith;
    }

    public void setUsingWith(boolean usingWith) {
        this.usingWith = usingWith;
    }

    public boolean isForce() { return force; }

    public void setForce(boolean force) { this.force = force; }

    protected void accept0(SQLASTVisitor visitor) {
        if (visitor instanceof PGASTVisitor) {
            accept0((PGASTVisitor) visitor);
        }
    }

    @Override
    public void accept0(PGASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, this.getName());
        }
        visitor.endVisit(this);
    }
}
