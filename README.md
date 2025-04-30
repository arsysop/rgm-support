## [ArSysOp](https://arsysop.ru/) [LOFT RGM](https://arsysop.ru/rgm/) Products Support

### Repository
In case 
 - you have a feature to propose for implementation
 - or a bug to report

add a note to an [existing open issue](https://github.com/arsysop/rgm-support/issues) that fits your needs or [create new one](https://github.com/arsysop/rgm-support/issues/new) (for logged in users).

See the list of [milestones](https://github.com/arsysop/rgm-support/milestones) to plan LOFT RGM products updates.

### Products
There are two standalone applications that help with C++ standard analysis
 - LOFT RGM Peeper for smart structured view and navigation through the standard
 - LOFT RGM Architect for mark-up and requirement gathering

There is also a library that supplies programmatic API for specification model access to create custom analysis instruments.

### How to use LOFT RGM Library

#### Prerequisites
Libraries can serve programmatic purposes of reading RGM Specification model in scope of an Eclipse application of any kind.
Using the Library requires
 - basic skills in Eclipse programming
 - basic experience with PDE 
 - knowledge of Eclipse application structure
 - practical acquaintance with EMF Framework

Also, the Library is built over Java 21, so it must be accessible.

#### Install license
License (`.licen` file) is to be placed in the folder `~\.passage\ru.arsysop.loft.rgm.view.product\1.0.0\`.

If you do not have a license, contact [ArSysOp Support team](mailto:support@arsysop.ru).

Without a license client code is going to fail with errors like this:

```
org.eclipse.passage.lic.api.LicensingException: No license coverage for feature 
identifier=ru.arsysop.loft.rgm.spec.load.encrypted, 
version=1.0.0, 
name=RGM Specification Load for Encrypted Model, 
provider=ArSysOp
```

#### Extend your target platform
Append additional _location_ to the target platform of your custom codebase:

```
		<location includeAllPlatforms="false" includeConfigurePhase="true" includeMode="planner" includeSource="true" type="InstallableUnit">
			<repository location="https://arsysop.ru/rgm/updates/milestone/latest/"/>
			<unit id="ru.arsysop.loft.rgm.library.peeper.feature.feature.group" version="0.0.0"/>
		</location>

```

#### Dependency
Append additional _dependency_ to your custom codebase. For instance, it will look like this for _MANIFEST.MF_ of your plug-in:

```
Require-Bundle: ru.arsysop.loft.rgm.spec.model;bundle-version="1.0.0"
```

#### Sample
See _ru.arsysop.loft.rgm.peeper.tests.PeeperTest_ in _example-src_ folder of this repository to meet actual code, 
that loads, reads and asserts Encrypted Specification Model content.

#### Collaboration
ArSysOp Team can help you with creating your own C++ standard analysis tools. 
