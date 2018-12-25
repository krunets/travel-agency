package by.runets.travelagency.converter;

public interface Converter<T, S> {
  T convert(S s);
}