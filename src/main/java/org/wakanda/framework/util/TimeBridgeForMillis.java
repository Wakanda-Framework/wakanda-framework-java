/* (C) 2025 WAKANDA FRAMEWORK */
package org.wakanda.framework.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.joda.time.DateTime;

public class TimeBridgeForMillis implements UserType<DateTime> {

  private final int _TYPE = Types.BIGINT;
  private final Class<DateTime> _CLASS = DateTime.class;

  @Override
  public int getSqlType() {
    return _TYPE;
  }

  @Override
  public Class<DateTime> returnedClass() {
    return _CLASS;
  }

  @Override
  public boolean equals(DateTime x, DateTime y) {
    if (x == null && y == null) {
      return true;
    }
    if (x == null || y == null) {
      return false;
    }
    return x.equals(y);
  }

  @Override
  public int hashCode(DateTime x) {
    assert (x != null);
    return x.hashCode();
  }

  @Override
  public DateTime nullSafeGet(
      ResultSet rs, int position, SharedSessionContractImplementor session, Object owner)
      throws SQLException {
    Long instance = rs.getLong(position);
    if (instance != 0) return new DateTime(instance);
    else return null;
  }

  @Override
  public void nullSafeSet(
      PreparedStatement st, DateTime value, int index, SharedSessionContractImplementor session)
      throws SQLException {
    if (Objects.isNull(value)) st.setNull(index, _TYPE);
    else st.setLong(index, value.getMillis());
  }

  @Override
  public DateTime deepCopy(DateTime value) {
    return value;
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(DateTime value) {
    return value;
  }

  @Override
  public DateTime assemble(Serializable cached, Object owner) {
    return (DateTime) cached;
  }
}
