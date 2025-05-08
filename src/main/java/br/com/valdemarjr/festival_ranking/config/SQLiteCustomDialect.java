package br.com.valdemarjr.festival_ranking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.dialect.AbstractDialect;
import org.springframework.data.relational.core.dialect.LimitClause;
import org.springframework.data.relational.core.dialect.LockClause;
import org.springframework.data.relational.core.sql.LockOptions;

@Configuration
public class SQLiteCustomDialect extends AbstractDialect {

  private static final LimitClause LIMIT_CLAUSE =
      new LimitClause() {
        @Override
        public String getLimit(long limit) {
          return "LIMIT " + limit;
        }

        @Override
        public String getOffset(long offset) {
          return String.format("LIMIT %d, 18446744073709551615", offset);
        }

        @Override
        public String getLimitOffset(long limit, long offset) {
          return String.format("LIMIT %s OFFSET %s", limit, offset);
        }

        @Override
        public Position getClausePosition() {
          return LimitClause.Position.AFTER_ORDER_BY;
        }
      };

  private static final LockClause LOCK_CLAUSE =
      new LockClause() {

        @Override
        public String getLock(LockOptions lockOptions) {
          return "WITH LOCK";
        }

        @Override
        public Position getClausePosition() {
          return Position.AFTER_ORDER_BY;
        }
      };

  @Override
  public LimitClause limit() {
    return LIMIT_CLAUSE;
  }

  @Override
  public LockClause lock() {
    return LOCK_CLAUSE;
  }
}
