import { registerPlugin } from '@capacitor/core';

import type { BackgroundVolumeButtonListenerPlugin } from './definitions';

const BackgroundVolumeButtonListener = registerPlugin<BackgroundVolumeButtonListenerPlugin>('BackgroundVolumeButtonListener', {
  web: () => import('./web').then(m => new m.BackgroundVolumeButtonListenerWeb()),
});

export * from './definitions';
export { BackgroundVolumeButtonListener };
