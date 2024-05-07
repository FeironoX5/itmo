/**
 * Provides classes for modeling rocket components and assemblies.
 * <p>
 * This package contains classes that represent various components used in rocket construction,
 * such as body components, assembly components, inner components, and more.
 * These classes are designed to facilitate the construction and modeling of rocket structures
 * by providing a hierarchy of reusable components with defined physical properties.
 * </p>
 * <p>
 * Key classes in this package include:
 * <ul>
 *     <li>{@link components.Component}: The base class for all rocket components.</li>
 *     <li>{@link components.assembly.AssemblyComponent}: Represents assembly components used in rocket construction.</li>
 *     <li>{@link components.body.BodyComponent}: Represents body components of a rocket, stored in {@link components.assembly.Stage}.</li>
 *     <li>{@link components.inner.InnerComponent}: Represents internal components within a rocket structure, stored in {@link components.body.BodyComponent}.</li>
 * </ul>
 * </p>
 * <p>
 * Classes in this package are authored by Gleb Kiva.
 * </p>
 *
 * @author Gleb Kiva
 */
package components;
