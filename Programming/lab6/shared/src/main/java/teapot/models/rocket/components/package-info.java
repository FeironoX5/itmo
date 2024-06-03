/**
 * Provides classes for modeling rocket components and assemblies.
 * <p>
 * This package contains classes that represent various components used in
 * rocket construction, such as body components, assembly components, inner
 * components.
 * These classes are designed to facilitate the construction and modeling of
 * rocket structures by providing a hierarchy of reusable components with
 * defined physical properties.
 * </p>
 * <p>
 * Key classes in this package are:
 * </p>
 * <ul>
 * <li>{@link teapot.models.rocket.components.Component}: The base class for all rocket
 * components.</li>
 * <li>{@link teapot.models.rocket.components.assembly.AssemblyComponent}: Represents
 * assembly components used in rocket construction.</li>
 * <li>{@link teapot.models.rocket.components.body.BodyComponent}: Represents body
 * components of a rocket, stored in
 * {@link teapot.models.rocket.components.assembly.Stage} instances.</li>
 * <li>{@link teapot.models.rocket.components.inner.InnerComponent}: Represents
 * internal components within
 * {@link teapot.models.rocket.components.body.BodyComponent} instances.</li>
 * </ul>
 *
 * @author Gleb Kiva
 */
package teapot.models.rocket.components;
