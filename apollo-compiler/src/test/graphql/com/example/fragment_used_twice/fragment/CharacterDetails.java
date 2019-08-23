// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_used_twice.fragment;

import com.apollographql.apollo.api.GraphqlFragment;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.Utils;
import com.example.fragment_used_twice.type.CustomType;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class CharacterDetails implements GraphqlFragment {
  static final ResponseField[] $responseFields = {
    ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forCustomType("birthDate", "birthDate", null, false, CustomType.DATE, Collections.<ResponseField.Condition>emptyList())
  };

  public static final String FRAGMENT_DEFINITION = "fragment CharacterDetails on Character {\n"
      + "  __typename\n"
      + "  name\n"
      + "  birthDate\n"
      + "}";

  public static final List<String> POSSIBLE_TYPES = Collections.unmodifiableList(Arrays.asList( "Human", "Droid"));

  final @NotNull String __typename;

  final @NotNull String name;

  final @NotNull Object birthDate;

  private transient volatile String $toString;

  private transient volatile int $hashCode;

  private transient volatile boolean $hashCodeMemoized;

  public CharacterDetails(@NotNull String __typename, @NotNull String name,
      @NotNull Object birthDate) {
    this.__typename = Utils.checkNotNull(__typename, "__typename == null");
    this.name = Utils.checkNotNull(name, "name == null");
    this.birthDate = Utils.checkNotNull(birthDate, "birthDate == null");
  }

  public @NotNull String __typename() {
    return this.__typename;
  }

  /**
   * The name of the character
   */
  public @NotNull String name() {
    return this.name;
  }

  /**
   * The date character was born.
   */
  public @NotNull Object birthDate() {
    return this.birthDate;
  }

  @SuppressWarnings("unchecked")
  public ResponseFieldMarshaller marshaller() {
    return new ResponseFieldMarshaller() {
      @Override
      public void marshal(ResponseWriter writer) {
        writer.writeString($responseFields[0], __typename);
        writer.writeString($responseFields[1], name);
        writer.writeCustom((ResponseField.CustomTypeField) $responseFields[2], birthDate);
      }
    };
  }

  @Override
  public String toString() {
    if ($toString == null) {
      $toString = "CharacterDetails{"
        + "__typename=" + __typename + ", "
        + "name=" + name + ", "
        + "birthDate=" + birthDate
        + "}";
    }
    return $toString;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof CharacterDetails) {
      CharacterDetails that = (CharacterDetails) o;
      return this.__typename.equals(that.__typename)
       && this.name.equals(that.name)
       && this.birthDate.equals(that.birthDate);
    }
    return false;
  }

  @Override
  public int hashCode() {
    if (!$hashCodeMemoized) {
      int h = 1;
      h *= 1000003;
      h ^= __typename.hashCode();
      h *= 1000003;
      h ^= name.hashCode();
      h *= 1000003;
      h ^= birthDate.hashCode();
      $hashCode = h;
      $hashCodeMemoized = true;
    }
    return $hashCode;
  }

  public static final class Mapper implements ResponseFieldMapper<CharacterDetails> {
    @Override
    public CharacterDetails map(ResponseReader reader) {
      final String __typename = reader.readString($responseFields[0]);
      final String name = reader.readString($responseFields[1]);
      final Object birthDate = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[2]);
      return new CharacterDetails(__typename, name, birthDate);
    }
  }
}