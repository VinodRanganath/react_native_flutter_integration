import type {TurboModule} from 'react-native';
import {TurboModuleRegistry} from 'react-native';

export interface Spec extends TurboModule {
  launch(value: string, callback: (value: string) => void): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>(
  'NativeFlutterModule',
) as Spec;
