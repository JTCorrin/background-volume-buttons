import { registerPlugin } from '@capacitor/core';

import type { BackgroundVolumeButtonListenerPlugin } from './definitions';

const BackgroundVolumeButtonListener =
  registerPlugin<BackgroundVolumeButtonListenerPlugin>(
    'BackgroundVolumeButtonListener',
  );

export * from './definitions';
export { BackgroundVolumeButtonListener };
