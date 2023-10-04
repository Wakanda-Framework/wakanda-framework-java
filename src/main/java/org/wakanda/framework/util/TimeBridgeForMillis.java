/* (C)2022 */
package org.wakanda.framework.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;
import org.joda.time.DateTime;

public class TimeBridgeForMillis implements UserType {

  @Override
  public int[] sqlTypes() {
    return new int[] {Types.BIGINT};
  }

  @Override
  public Class returnedClass() {
    return DateTime.class;
  }

  @Override
  public boolean equals(Object instance1, Object instance2) throws HibernateException {
    if (instance1 == null && instance2 == null) {
      return true;
    }
    if (instance1 == null || instance2 == null) {
      return false;
    }

    return instance1.equals(instance2);
  }

  @Override
  public int hashCode(Object object) throws HibernateException {
    assert (object != null);
    return object.hashCode();
  }

  @Override
  public Object nullSafeGet(
      ResultSet resultSet,
      String[] references,
      SharedSessionContractImplementor sharedSessionContractImplementor,
      Object owner)
      throws HibernateException, SQLException {
    Object instance =
        StandardBasicTypes.LONG.nullSafeGet(
            resultSet, references, sharedSessionContractImplementor, owner);
    if (instance == null) {
      return null;
    }
    return new DateTime(instance);
  }

  @Override
  public void nullSafeSet(
      PreparedStatement preparedStatement,
      Object valueInMillis,
      int index,
      SharedSessionContractImplementor sharedSessionContractImplementor)
      throws HibernateException, SQLException {
    if (valueInMillis != null) {
      StandardBasicTypes.LONG.nullSafeSet(
          preparedStatement,
          ((DateTime) valueInMillis).getMillis(),
          index,
          sharedSessionContractImplementor);
    } else {
      StandardBasicTypes.LONG.nullSafeSet(
          preparedStatement, null, index, sharedSessionContractImplementor);
    }
  }

  @Override
  public Object deepCopy(Object object) throws HibernateException {
    return object;
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(Object object) throws HibernateException {
    return (Serializable) object;
  }

  @Override
  public Object assemble(Serializable serializableCache, Object value) throws HibernateException {
    return serializableCache;
  }

  @Override
  public Object replace(Object pioneer, Object target, Object owner) throws HibernateException {
    return pioneer;
  }
}
